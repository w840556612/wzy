package com.ipinyou.base.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.TemplateLoader;

/**
 * @author lijt
 * @see http
 *      ://watchitlater.com/blog/2011/10/default-html-escape-using-freemarker/
 * @see http
 *      ://stackoverflow.com/questions/1265488/default-escaping-in-freemarker
 * @see http
 *      ://techdiary.peterbecker.de/2009/02/defending-against-xss-attacks-in.
 *      html
 *      
 * @see http://stackoverflow.com/questions/11289203/reading-requestparameters-in-freemarker-template-with-spring-mvc
 * @see http://forum.springsource.org/archive/index.php/t-32846.html
 */
public class HtmlFreeMarkerConfigurer extends FreeMarkerConfigurer {
	@Override
	protected TemplateLoader getAggregateTemplateLoader(
			List<TemplateLoader> templateLoaders) {
		logger.info("Using HtmlTemplateLoader to enforce HTML-safe content");
		return new HtmlTemplateLoader(
				super.getAggregateTemplateLoader(templateLoaders));
	}
}

class HtmlTemplateLoader implements TemplateLoader {

	public static final String ESCAPE_PREFIX = "<#ftl strip_whitespace=true><#escape x as x?html>";
	public static final String ESCAPE_SUFFIX = "</#escape>";

	private final TemplateLoader delegate;

	public HtmlTemplateLoader(TemplateLoader delegate) {
		this.delegate = delegate;
	}

	@Override
	public Object findTemplateSource(String name) throws IOException {
		return delegate.findTemplateSource(name);
	}

	@Override
	public long getLastModified(Object templateSource) {
		return delegate.getLastModified(templateSource);
	}

	@Override
	public Reader getReader(Object templateSource, String encoding)
			throws IOException {
		Reader reader = delegate.getReader(templateSource, encoding);
		try {
			String templateText = IOUtils.toString(reader);
			return new StringReader(ESCAPE_PREFIX + templateText
					+ ESCAPE_SUFFIX);
		} finally {
			IOUtils.closeQuietly(reader);
		}
	}

	@Override
	public void closeTemplateSource(Object templateSource) throws IOException {
		delegate.closeTemplateSource(templateSource);
	}
}