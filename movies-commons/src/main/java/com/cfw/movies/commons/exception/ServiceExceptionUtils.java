package com.cfw.movies.commons.exception;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Helper class for implementing exception classes which are capable of
 * holding nested exceptions. Necessary because we can't share a base
 * class among different exception types.
 *
 * <p>Mainly for use within the framework.</p>
 *
 * @since 1.0
 * @see ServiceException
 * @see ServiceRuntimeException
 * @see org.springframework.core.NestedExceptionUtils
 */
public class ServiceExceptionUtils {

	private static Properties prop;
	
	static {
		try 
		{
			prop = PropertiesLoaderUtils.loadProperties(new ClassPathResource("service-exception.properties"));
		} 
		catch (IOException e) 
		{
			throw new RuntimeException("Failed to read file (service-exception.properties)");
		}
	}
	
	/**
	 * Build a message for the given base message and root cause.
	 * @param message the base message
	 * @param cause the root cause
	 * @return the full exception message
	 */
	public static String buildMessage(String message, Throwable cause) {
		if (cause != null) {
			StringBuilder sb = new StringBuilder();
			if (message != null) {
				sb.append(message).append(";");
			}
			sb.append("nested exception is ").append(cause);
			return sb.toString();
		}
		else {
			return message;
		}
	}
	
	public static String getErrorMessage(Class<? extends Exception> clazz) {
		
		return prop.getProperty(clazz.getSimpleName(), "业务异常");
	}
	
}
