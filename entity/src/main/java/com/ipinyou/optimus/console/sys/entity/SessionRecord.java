/**
 * 
 */
package com.ipinyou.optimus.console.sys.entity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.StoppedSessionException;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipinyou.base.model.LoginInfo;

/**
 * @author lijt
 * 
 */
@Entity
@Table(name = "sys_session_record")
@Setter
@Getter
public class SessionRecord implements com.ipinyou.base.entity.Entity,
		ValidatingSession, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 655318035554331677L;

	/**
	 * 日志记录实例
	 */
	protected final transient Logger logger = LoggerFactory
			.getLogger(getClass());
	@Id
	@Column(length = 255, nullable = false)
	private String id;
	private Date startTimestamp;
	private Date stopTimestamp;
	private Date lastAccessTime;
	private long timeout;
	private boolean expired;
	@Column(length = 255)
	private String host;
	@Transient
	private transient Map<Object, Object> attributes;
	// @Lob
	// private byte[] attributesData;
	@Lob
	private byte[] attributesData2;
	
	private Long userId;
	
	/**
	 * 登录认证方式
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 50)
	private AuthMethod authMethod;
	
	public static enum AuthMethod {
		FormAuth("Web表单登录验证"),
		ApiAuth("API登录验证");

		private String text;

		private AuthMethod(String text) {
			this.text = text;
		}

		public String getText() {
			return text;
		}
	}
	public SessionRecord() {
		this.timeout = 2 * 60 * 60;
		this.startTimestamp = new Date();
		this.lastAccessTime = this.startTimestamp;
	}

	public SessionRecord(String host) {
		this();
		this.host = host;
	}

	// private Long userId;
	// private Long realUserId;
	//
	// private String account;
	// private String realAccount;
	//
	// private String name;
	// private String realName;
	//
	// /**
	// * 用户类型
	// */
	// private int type;
	// /**
	// * 用户所在的池id
	// */
	// private Long poolId;
	// /**
	// * express版用户对应的广告主id
	// */
	// private Long exAdvertiserId;

	// static Serializer<SavedRequest> savedRequestSerializer = new
	// Serializer<SavedRequest>() {
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * com.esotericsoftware.kryo.Serializer#write(com.esotericsoftware.kryo
	// * .Kryo, com.esotericsoftware.kryo.io.Output, java.lang.Object)
	// */
	// @Override
	// public void write(Kryo kryo, Output output, SavedRequest object) {
	// output.writeString(object.getMethod());
	// output.writeString(object.getQueryString());
	// output.writeString(object.getRequestURI());
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// * com.esotericsoftware.kryo.Serializer#read(com.esotericsoftware.kryo
	// * .Kryo, com.esotericsoftware.kryo.io.Input, java.lang.Class)
	// */
	// @Override
	// public SavedRequest read(Kryo kryo, Input input,
	// Class<SavedRequest> type) {
	// String method = input.readString();
	// String queryString = input.readString();
	// String requestURI = input.readString();
	// MockHttpServletRequest r = new MockHttpServletRequest(method,
	// requestURI);
	// r.setQueryString(queryString);
	// return new SavedRequest(r);
	// }
	// };

	// @PrePersist
	// @PreUpdate
	public synchronized void serialize() {
		if (attributes != null && attributes.size() != 0) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput out = null;
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(attributes);
				attributesData2 = bos.toByteArray();
			} catch (IOException e) {
				// logger.error(ExceptionUtils.getStackTrace(e));
				// throw new RuntimeException(e);
				logger.error("serialize session attribute error: ", e);// 忽略异常
				try {
					FileUtils.writeByteArrayToFile(File.createTempFile(
							"serialize", ".data"), SerializationUtils
							.serialize((Serializable) attributes));
				} catch (IOException e1) {
					logger.error("write serialize error data error: ", e);
				}
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
					throw new RuntimeException(e);
				}
				IOUtils.closeQuietly(bos);
			}
			// Kryo kryo = new KryoReflectionFactorySupport(); // can only be
			// used
			// // with sun/oracly
			// // jdk! see:
			// // https://github.com/magro/kryo-serializers/
			// //
			// https://code.google.com/p/memcached-session-manager/wiki/SerializationStrategies
			// // kryo.register(SavedRequest.class, savedRequestSerializer);
			// Output output = new Output(bos);
			// try {
			// kryo.writeObject(output, attributes);
			// } catch (Throwable e) {
			// logger.error("serialize session attribute error: ", e);// 忽略异常
			// try {
			// FileUtils.writeByteArrayToFile(File.createTempFile(
			// "serialize", ".data"), SerializationUtils
			// .serialize((Serializable) attributes));
			// } catch (IOException e1) {
			// logger.error("write serialize error data error: ", e);
			// }
			// }
			// output.close();
			// attributesData = bos.toByteArray();
		} else {
			attributesData2 = null;
		}
		// if (attributes != null && attributes.size() != 0) {
		// attributesStr = JSON.toJSONString(attributes,
		// SerializerFeature.WriteClassName,
		// SerializerFeature.WriteNullListAsEmpty);
		// } else {
		// attributesStr = null;
		// }
		// ObjectMapper objectMapper = new ObjectMapper();
		// objectMapper.enableDefaultTyping(DefaultTyping.NON_FINAL);
		// StringWriter sw = new StringWriter();
		// try {
		// objectMapper.writeValue(sw, attributes);
		// attributesStr = sw.toString();
		// } catch (IOException e) {
		// logger.error(ExceptionUtils.getStackTrace(e));
		// throw new RuntimeException(e);
		// }
	}

	// @Transient
	// private transient boolean deserialized = false;

	@SuppressWarnings("unchecked")
	// @PostLoad
	public synchronized void deserialize() {
		// if (deserialized) {
		// return;
		// }
		if (attributesData2 != null && attributesData2.length > 0) {
			ByteArrayInputStream bis = new ByteArrayInputStream(attributesData2);
			ObjectInput in = null;
			try {
				in = new ObjectInputStream(bis);
				attributes = (Map<Object, Object>) in.readObject();
			} catch (IOException | ClassNotFoundException e) {
				// logger.error(ExceptionUtils.getStackTrace(e));
				// throw new RuntimeException(e);
				try {
					FileUtils.writeByteArrayToFile(
							File.createTempFile("deserialize", ".data"),
							attributesData2);
				} catch (IOException e1) {
					logger.error("write deserialize error data error: ", e);
				}
			} finally {
				IOUtils.closeQuietly(bis);
				try {
					in.close();
				} catch (IOException e) {
					logger.error(ExceptionUtils.getStackTrace(e));
					throw new RuntimeException(e);
				}
			}
			// Kryo kryo = new KryoReflectionFactorySupport();
			// // kryo.register(SavedRequest.class, savedRequestSerializer);
			// Input input = new Input(new
			// ByteArrayInputStream(attributesData));
			// try {
			// attributes = kryo.readObject(input, HashMap.class);
			// } catch (Throwable e) {
			// logger.error("deserialize session attribute error: ", e);// 忽略异常
			// try {
			// FileUtils.writeByteArrayToFile(
			// File.createTempFile("deserialize", ".data"),
			// attributesData);
			// } catch (IOException e1) {
			// logger.error("write deserialize error data error: ", e);
			// }
			// }
			// input.close();
		} else {
			attributes = null;
		}
		// deserialized = true;
		// if (!StringUtils.isBlank(attributesStr)) {
		// attributes = (Map<Object, Object>) JSON.parse(attributesStr);
		// } else {
		// attributes = null;
		// }
		// ObjectMapper objectMapper = new ObjectMapper();
		// try {
		// attributes = objectMapper.readValue(attributesStr, Map.class);
		// } catch (IOException e) {
		// logger.error(ExceptionUtils.getStackTrace(e));
		// throw new RuntimeException(e);
		// }
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.Session#touch()
	 */
	@Override
	public void touch() throws InvalidSessionException {
		Date d = new Date();
		if (lastAccessTime == null
				|| d.getTime() - lastAccessTime.getTime() >= 10 * 60 * 1000) {// 最后访问时间超过10分钟才更新一次
			this.lastAccessTime = new Date();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.Session#stop()
	 */
	@Override
	public void stop() throws InvalidSessionException {
		if (this.stopTimestamp == null) {
			this.stopTimestamp = new Date();
		}
	}

	private Map<Object, Object> getAttributesLazy() {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			attributes = new HashMap<Object, Object>();
			setAttributes(attributes);
		}
		return attributes;
	}

	@Transient
	public Collection<Object> getAttributeKeys() throws InvalidSessionException {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			return Collections.emptySet();
		}
		return attributes.keySet();
	}
	
	public transient final static String REALM_NAME_WEB_FORM = "FORM_AUTHENTICATION";
	
	private void setUserId(Object spc){
		if(spc instanceof SimplePrincipalCollection){
			Collection<?> c = ((SimplePrincipalCollection)spc).fromRealm("FORM_AUTHENTICATION");
			if(c!=null&&c.size()>0){
				Object o = c.iterator().next();
				if(o instanceof LoginInfo){
					authMethod = AuthMethod.FormAuth;
					userId = ((LoginInfo)o).getRealUserId();
				}
			}
			}
	}
	
	@Transient
	public Object getAttribute(Object key) {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			return null;
		}
		return attributes.get(key);
	}

	public void setAttribute(Object key, Object value) {
		if (value == null) {
			removeAttribute(key);
		} else {
			if(DefaultSubjectContext.PRINCIPALS_SESSION_KEY.equals(key)){
				setUserId(value);
			}
			getAttributesLazy().put(key, value);
		}
		this.lastAccessTime = new Date();
	}

	public Object removeAttribute(Object key) {
		Map<Object, Object> attributes = getAttributes();
		if (attributes == null) {
			return null;
		} else {
			this.lastAccessTime = new Date();
			if(DefaultSubjectContext.PRINCIPALS_SESSION_KEY.equals(key)){
				userId = null;
				authMethod = null;
			}
			return attributes.remove(key);
		}
	}

	@Transient
	protected boolean isStopped() {
		return getStopTimestamp() != null;
	}

	protected void expire() {
		stop();
		this.expired = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.mgt.ValidatingSession#isValid()
	 */
	@Override
	public boolean isValid() {
		return !isStopped() && !isExpired();
	}

	/**
	 * Determines if this session is expired.
	 * 
	 * @return true if the specified session has expired, false otherwise.
	 */
	@Transient
	protected boolean isTimedOut() {

		if (isExpired()) {
			return true;
		}

		long timeout = getTimeout();

		if (timeout >= 0l) {

			Date lastAccessTime = getLastAccessTime();

			if (lastAccessTime == null) {
				String msg = "session.lastAccessTime for session with id ["
						+ getId()
						+ "] is null.  This value must be set at "
						+ "least once, preferably at least upon instantiation.  Please check the "
						+ getClass().getName()
						+ " implementation and ensure "
						+ "this value will be set (perhaps in the constructor?)";
				throw new IllegalStateException(msg);
			}

			// Calculate at what time a session would have been last accessed
			// for it to be expired at this point. In other words, subtract
			// from the current time the amount of time that a session can
			// be inactive before expiring. If the session was last accessed
			// before this time, it is expired.
			long expireTimeMillis = System.currentTimeMillis() - timeout;
			Date expireTime = new Date(expireTimeMillis);
			return lastAccessTime.before(expireTime);
		} else {
			if (logger.isTraceEnabled()) {
				logger.trace("No timeout for session with id [" + getId()
						+ "].  Session is not considered expired.");
			}
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.mgt.ValidatingSession#validate()
	 */
	@Override
	public void validate() throws InvalidSessionException {
		// check for stopped:
		if (isStopped()) {
			// timestamp is set, so the session is considered stopped:
			String msg = "Session with id ["
					+ getId()
					+ "] has been "
					+ "explicitly stopped.  No further interaction under this session is "
					+ "allowed.";
			throw new StoppedSessionException(msg);
		}

		// check for expiration
		if (isTimedOut()) {
			expire();

			// throw an exception explaining details of why it expired:
			Date lastAccessTime = getLastAccessTime();
			long timeout = getTimeout();

			Serializable sessionId = getId();

			DateFormat df = DateFormat.getInstance();
			String msg = "Session with id [" + sessionId + "] has expired. "
					+ "Last access time: " + df.format(lastAccessTime)
					+ ".  Current time: " + df.format(new Date())
					+ ".  Session timeout is set to " + timeout / 1000
					+ " seconds (" + timeout / 1000 + " minutes)";
			if (logger.isTraceEnabled()) {
				logger.trace(msg);
			}
			throw new ExpiredSessionException(msg);
		}
	}

	/**
	 * Returns {@code true} if the specified argument is an {@code instanceof}
	 * {@code SimpleSession} and both {@link #getId() id}s are equal. If the
	 * argument is a {@code SimpleSession} and either 'this' or the argument
	 * does not yet have an ID assigned, the value of
	 * {@link #onEquals(SimpleSession) onEquals} is returned, which does a
	 * necessary attribute-based comparison when IDs are not available.
	 * <p/>
	 * Do your best to ensure {@code SimpleSession} instances receive an ID very
	 * early in their lifecycle to avoid the more expensive attributes-based
	 * comparison.
	 * 
	 * @param obj
	 *            the object to compare with this one for equality.
	 * @return {@code true} if this object is equivalent to the specified
	 *         argument, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof SessionRecord) {
			SessionRecord other = (SessionRecord) obj;
			Serializable thisId = getId();
			Serializable otherId = other.getId();
			if (thisId != null && otherId != null) {
				return thisId.equals(otherId);
			} else {
				// fall back to an attribute based comparison:
				return onEquals(other);
			}
		}
		return false;
	}

	/**
	 * Provides an attribute-based comparison (no ID comparison) - incurred
	 * <em>only</em> when 'this' or the session object being compared for
	 * equality do not have a session id.
	 * 
	 * @param ss
	 *            the SimpleSession instance to compare for equality.
	 * @return true if all the attributes, except the id, are equal to this
	 *         object's attributes.
	 * @since 1.0
	 */
	protected boolean onEquals(SessionRecord ss) {
		return (getStartTimestamp() != null ? getStartTimestamp().equals(
				ss.getStartTimestamp()) : ss.getStartTimestamp() == null)
				&& (getStopTimestamp() != null ? getStopTimestamp().equals(
						ss.getStopTimestamp()) : ss.getStopTimestamp() == null)
				&& (getLastAccessTime() != null ? getLastAccessTime().equals(
						ss.getLastAccessTime())
						: ss.getLastAccessTime() == null)
				&& (getTimeout() == ss.getTimeout())
				&& (isExpired() == ss.isExpired())
				&& (getHost() != null ? getHost().equals(ss.getHost()) : ss
						.getHost() == null)
				&& (getAttributes() != null ? getAttributes().equals(
						ss.getAttributes()) : ss.getAttributes() == null);
	}

	/**
	 * Returns the hashCode. If the {@link #getId() id} is not {@code null}, its
	 * hashcode is returned immediately. If it is {@code null}, an
	 * attributes-based hashCode will be calculated and returned.
	 * <p/>
	 * Do your best to ensure {@code SimpleSession} instances receive an ID very
	 * early in their lifecycle to avoid the more expensive attributes-based
	 * calculation.
	 * 
	 * @return this object's hashCode
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		Serializable id = getId();
		if (id != null) {
			return id.hashCode();
		}
		int hashCode = getStartTimestamp() != null ? getStartTimestamp()
				.hashCode() : 0;
		hashCode = 31
				* hashCode
				+ (getStopTimestamp() != null ? getStopTimestamp().hashCode()
						: 0);
		hashCode = 31
				* hashCode
				+ (getLastAccessTime() != null ? getLastAccessTime().hashCode()
						: 0);
		hashCode = 31 * hashCode
				+ Long.valueOf(Math.max(getTimeout(), 0)).hashCode();
		hashCode = 31 * hashCode + Boolean.valueOf(isExpired()).hashCode();
		hashCode = 31 * hashCode
				+ (getHost() != null ? getHost().hashCode() : 0);
		hashCode = 31 * hashCode
				+ (getAttributes() != null ? getAttributes().hashCode() : 0);
		return hashCode;
	}

	/**
	 * Returns the string representation of this SimpleSession, equal to
	 * <code>getClass().getName() + &quot;,id=&quot; + getId()</code>.
	 * 
	 * @return the string representation of this SimpleSession, equal to
	 *         <code>getClass().getName() + &quot;,id=&quot; + getId()</code>.
	 * @since 1.0
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getName()).append(",id=").append(getId());
		return sb.toString();
	}

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		byte[] b = FileUtils.readFileToByteArray(new File(
				"/home/lijt/deserialize2932842674823935393.data"));
		Object o = SerializationUtils.deserialize(b);
		System.out.println(o);
	}
}
