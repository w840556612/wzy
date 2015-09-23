/**
 * 
 */
package com.ipinyou.base.spring;

import java.beans.PropertyEditorSupport;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefaults;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * @author lijt
 * 
 */
public class PageableArgumentResolver implements WebArgumentResolver {

	private static final Pageable DEFAULT_PAGE_REQUEST = new PageRequest(0, 10);
	private static final String DEFAULT_PREFIX = "page";
	private static final String DEFAULT_SEPARATOR = ".";

	private Pageable fallbackPagable = DEFAULT_PAGE_REQUEST;
	private String prefix = DEFAULT_PREFIX;
	private String separator = DEFAULT_SEPARATOR;

	/**
	 * Setter to configure a fallback instance of {@link Pageable} that is being
	 * used to back missing parameters. Defaults to
	 * {@value #DEFAULT_PAGE_REQUEST}.
	 * 
	 * @param fallbackPagable
	 *            the fallbackPagable to set
	 */
	public void setFallbackPagable(Pageable fallbackPagable) {
		this.fallbackPagable = null == fallbackPagable ? DEFAULT_PAGE_REQUEST
				: fallbackPagable;
	}

	/**
	 * Setter to configure the prefix of request parameters to be used to
	 * retrieve paging information. Defaults to {@link #DEFAULT_PREFIX}.
	 * 
	 * @param prefix
	 *            the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = null == prefix ? DEFAULT_PREFIX : prefix;
	}

	/**
	 * Setter to configure the separator between prefix and actual property
	 * value. Defaults to {@link #DEFAULT_SEPARATOR}.
	 * 
	 * @param separator
	 *            the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = null == separator ? DEFAULT_SEPARATOR : separator;
	}

	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());

	private final static String PAGE_SIZE_COOKIE_KEY = "page.size";

	/**
	 * 若参数值为true,表示页面总条数不写入cookie
	 */
	public final static String PARAM_NOT_STORE = "page.transient";

	private final static int PAGE_SIZE_COOKIE_MAX_AGE = 24 * 3600 * 30;

	private final static int PAGE_SIZE_LIMIT = 500;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.bind.support.WebArgumentResolver#resolveArgument
	 * (org.springframework.core.MethodParameter,
	 * org.springframework.web.context.request.NativeWebRequest)
	 */
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) {

		if (methodParameter.getParameterType().equals(Pageable.class)) {

			assertPageableUniqueness(methodParameter);

			Pageable request = getDefaultFromAnnotationOrFallback(methodParameter);
			int cookiePageSize = getPageSizeFromCookie(webRequest
					.getNativeRequest(HttpServletRequest.class));
			if (cookiePageSize != 0) {
				request = new PageRequest(request.getPageNumber(),
						cookiePageSize, request.getSort());
			}
			int defaultPageSize = request.getPageSize();
			ServletRequest servletRequest = (ServletRequest) webRequest
					.getNativeRequest();
			PropertyValues propertyValues = new ServletRequestParameterPropertyValues(
					servletRequest, getPrefix(methodParameter), separator);

			DataBinder binder = new ServletRequestDataBinder(request);

			binder.initDirectFieldAccess();
			binder.registerCustomEditor(Sort.class, new SortPropertyEditor(
					"sort.dir", propertyValues));
			binder.bind(propertyValues);
			if (request.getPageSize() > PAGE_SIZE_LIMIT) {
				throw new IllegalArgumentException("每页记录数"
						+ request.getPageSize() + "超过上限" + PAGE_SIZE_LIMIT);
			}
			if (request.getPageSize() != defaultPageSize
					&& !"true".equals(webRequest.getParameter(PARAM_NOT_STORE))) {
				setPageSizeToCookie(
						webRequest.getNativeResponse(HttpServletResponse.class),
						request.getPageSize());
			}
			if (request.getPageNumber() > 0) {
				request = new PageRequest(request.getPageNumber() - 1,
						request.getPageSize(), request.getSort());
			}

			return request;
		}

		return UNRESOLVED;
	}

	private int getPageSizeFromCookie(HttpServletRequest req) {
		Cookie[] cs = req.getCookies();
		if(cs==null){
			return 0;
		}
		for (Cookie c : cs) {
			if (PAGE_SIZE_COOKIE_KEY.equalsIgnoreCase(c.getName())) {
				try {
					int size = Integer.parseInt(c.getValue());
					if (size > 0 && size <= PAGE_SIZE_LIMIT) {
						return size;
					} else {
						return 0;
					}
				} catch (NumberFormatException e) {
					logger.warn(
							"read page.size from cookie failure, error info: ",
							e);
					return 0;
				}
			}
		}
		return 0;
	}

	private void setPageSizeToCookie(HttpServletResponse res, int pageSize) {
		Cookie cookie = new Cookie(PAGE_SIZE_COOKIE_KEY,
				String.valueOf(pageSize));
		cookie.setMaxAge(PAGE_SIZE_COOKIE_MAX_AGE);
		cookie.setPath("/");
		res.addCookie(cookie);
	}

	private Pageable getDefaultFromAnnotationOrFallback(
			MethodParameter methodParameter) {

		// search for PageableDefaults annotation
		for (Annotation annotation : methodParameter.getParameterAnnotations()) {
			if (annotation instanceof PageableDefaults) {
				return getDefaultPageRequestFrom((PageableDefaults) annotation);
			}
		}

		// Construct request with fallback request to ensure sensible
		// default values. Create fresh copy as Spring will manipulate the
		// instance under the covers
		return new PageRequest(fallbackPagable.getPageNumber(),
				fallbackPagable.getPageSize(), fallbackPagable.getSort());
	}

	private static Pageable getDefaultPageRequestFrom(PageableDefaults defaults) {

		// +1 is because we substract 1 later
		int defaultPageNumber = defaults.pageNumber() + 1;
		int defaultPageSize = defaults.value();

		if (defaults.sort().length == 0) {
			return new PageRequest(defaultPageNumber, defaultPageSize);
		}

		return new PageRequest(defaultPageNumber, defaultPageSize,
				defaults.sortDir(), defaults.sort());
	}

	/**
	 * Resolves the prefix to use to bind properties from. Will prepend a
	 * possible {@link Qualifier} if available or return the configured prefix
	 * otherwise.
	 * 
	 * @param parameter
	 * @return
	 */
	private String getPrefix(MethodParameter parameter) {

		for (Annotation annotation : parameter.getParameterAnnotations()) {
			if (annotation instanceof Qualifier) {
				return new StringBuilder(((Qualifier) annotation).value())
						.append("_").append(prefix).toString();
			}
		}

		return prefix;
	}

	/**
	 * Asserts uniqueness of all {@link Pageable} parameters of the method of
	 * the given {@link MethodParameter}.
	 * 
	 * @param parameter
	 */
	private void assertPageableUniqueness(MethodParameter parameter) {

		Method method = parameter.getMethod();

		if (containsMoreThanOnePageableParameter(method)) {
			Annotation[][] annotations = method.getParameterAnnotations();
			assertQualifiersFor(method.getParameterTypes(), annotations);
		}
	}

	/**
	 * Returns whether the given {@link Method} has more than one
	 * {@link Pageable} parameter.
	 * 
	 * @param method
	 * @return
	 */
	private boolean containsMoreThanOnePageableParameter(Method method) {

		boolean pageableFound = false;

		for (Class<?> type : method.getParameterTypes()) {

			if (pageableFound && type.equals(Pageable.class)) {
				return true;
			}

			if (type.equals(Pageable.class)) {
				pageableFound = true;
			}
		}

		return false;
	}

	/**
	 * Asserts that every {@link Pageable} parameter of the given parameters
	 * carries an {@link Qualifier} annotation to distinguish them from each
	 * other.
	 * 
	 * @param parameterTypes
	 * @param annotations
	 */
	private void assertQualifiersFor(Class<?>[] parameterTypes,
			Annotation[][] annotations) {

		Set<String> values = new HashSet<String>();

		for (int i = 0; i < annotations.length; i++) {

			if (Pageable.class.equals(parameterTypes[i])) {

				Qualifier qualifier = findAnnotation(annotations[i]);

				if (null == qualifier) {
					throw new IllegalStateException(
							"Ambiguous Pageable arguments in handler method. If you use multiple parameters of type Pageable you need to qualify them with @Qualifier");
				}

				if (values.contains(qualifier.value())) {
					throw new IllegalStateException(
							"Values of the user Qualifiers must be unique!");
				}

				values.add(qualifier.value());
			}
		}
	}

	/**
	 * Returns a {@link Qualifier} annotation from the given array of
	 * {@link Annotation}s. Returns {@literal null} if the array does not
	 * contain a {@link Qualifier} annotation.
	 * 
	 * @param annotations
	 * @return
	 */
	private Qualifier findAnnotation(Annotation[] annotations) {

		for (Annotation annotation : annotations) {
			if (annotation instanceof Qualifier) {
				return (Qualifier) annotation;
			}
		}

		return null;
	}

	/**
	 * {@link java.beans.PropertyEditor} to create {@link Sort} instances from
	 * textual representations. The implementation interprets the string as a
	 * comma separated list where the first entry is the sort direction (
	 * {@code asc}, {@code desc}) followed by the properties to sort by.
	 * 
	 * @author Oliver Gierke
	 */
	private static class SortPropertyEditor extends PropertyEditorSupport {

		private final String orderProperty;
		private final PropertyValues values;

		/**
		 * Creates a new {@link SortPropertyEditor}.
		 * 
		 * @param orderProperty
		 * @param values
		 */
		public SortPropertyEditor(String orderProperty, PropertyValues values) {

			this.orderProperty = orderProperty;
			this.values = values;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
		 */
		@Override
		public void setAsText(String text) {

			PropertyValue rawOrder = values.getPropertyValue(orderProperty);
			Direction order = null == rawOrder ? Direction.ASC : Direction
					.fromString(rawOrder.getValue().toString());

			setValue(new Sort(order, text));
		}
	}
}
