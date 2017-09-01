package com.cfw.movies.manage.service;


import com.cfw.movies.commons.exception.ServiceException;

import java.io.InputStream;

public interface UploadService {

	/**
	 * 保存文件到临时目录
	 * 
	 * @param 	source				文件流
	 * @param 	originalFileName	原始文件名
	 * @return 	String				保存的临时文件名
	 * @throws 	ServiceException
	 */
	public String uploadTempFile(InputStream source, String originalFileName) throws ServiceException, ServiceException;
	
	/**
	 * 保存临时目录中的文件到持久化目录
	 * 
	 * @param 	tempFileName		临时文件名
	 * @param	pathProperties		配置文件中该文件存储前缀路径的属性名
	 * @return 	String				保存的文件路径
	 * @throws 	ServiceException
	 */
	public String persistTempFile(String tempFileName, String pathProperties) throws ServiceException;
	
	/**
	 * 保存文件到持久化目录
	 * 
	 * @param 	source				原始文件输入流
	 * @param 	originalFileName	原始文件名
	 * @param	pathProperties		配置文件中该文件存储前缀路径的属性名
	 * @return	String				保存的文件路径
	 * @throws 	ServiceException
	 */
	public String uploadPersistenceFile(InputStream source, String originalFileName, String pathProperties) throws ServiceException;
	
	
}
