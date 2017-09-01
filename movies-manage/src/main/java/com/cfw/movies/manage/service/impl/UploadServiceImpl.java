package com.cfw.movies.manage.service.impl;

import com.cfw.movies.commons.exception.FileHouseException;
import com.cfw.movies.commons.exception.ServiceException;
import com.cfw.movies.commons.utils.FileUtils;
import com.cfw.movies.manage.service.PersistenceDirectoryService;
import com.cfw.movies.manage.service.TempDirectoryService;
import com.cfw.movies.manage.service.UploadService;
import com.cfw.movies.manage.service.file.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;


@Service("uploadService")
public class UploadServiceImpl implements UploadService {

	@Autowired
	private FileUploadService fileUploadHandler;
	@Autowired
	private TempDirectoryService tempDirectoryManager;
	@Autowired
	private PersistenceDirectoryService persistenceDirectoryManager;
	
	/**
	 * 保存文件到临时目录
	 * 
	 * @param 	source				文件流
	 * @param 	originalFileName	原始文件名
	 * @return 	String				保存的临时文件名
	 * @throws 	ServiceException
	 */
	@Override
	public String uploadTempFile(InputStream source, String originalFileName) throws ServiceException {

		try
		{
			String output = tempDirectoryManager.generateTempFilePath(originalFileName);

			fileUploadHandler.upload(source, output);

			return FileUtils.truncateFileName(output);
		}
		catch (FileHouseException e)
		{
			throw new ServiceException("上传临时文件失败", e);
		}
		
	}
	
	/**
	 * 保存临时目录中的文件到持久化目录
	 * 
	 * @param 	tempFileName		临时文件名
	 * @param	pathProperties		配置文件中该文件存储前缀路径的属性名
	 * @return 	String				保存的文件路径
	 * @throws 	ServiceException
	 */
	@Override
	public String persistTempFile(String tempFileName, String pathProperties) throws ServiceException {

		try
		{
			String source = tempDirectoryManager.obtainTempFilePath(tempFileName);

			String output = persistenceDirectoryManager.generatePersistenceFilePath(tempFileName, pathProperties);

			fileUploadHandler.upload(source, output);

			return persistenceDirectoryManager.truncatePersistenceFilePath(output);
		}
		catch (FileHouseException e)
		{
			throw new ServiceException("保存临时文件失败", e);
		}
	}
	
	/**
	 * 保存文件到持久化目录
	 * 
	 * @param 	source				原始文件输入流
	 * @param 	originalFileName	原始文件名
	 * @param	pathProperties		配置文件中该文件存储前缀路径的属性名
	 * @return	String				保存的文件路径
	 * @throws 	ServiceException
	 */
	@Override
	public String uploadPersistenceFile(InputStream source, String originalFileName, String pathProperties) throws ServiceException {

		try
		{
			String output = persistenceDirectoryManager.generatePersistenceFilePath(originalFileName, pathProperties);

			fileUploadHandler.upload(source, output);

			return persistenceDirectoryManager.truncatePersistenceFilePath(output);
		}
		catch (FileHouseException e)
		{
			throw new ServiceException("保存临时文件失败", e);
		}
	}


}
