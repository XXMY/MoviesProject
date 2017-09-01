package com.cfw.movies.manage.service.impl;

import com.cfw.movies.commons.exception.ServiceException;
import com.cfw.movies.commons.utils.CodeHelper;
import com.cfw.movies.commons.utils.Constant;
import com.cfw.movies.commons.utils.FileTypes;
import com.cfw.movies.manage.service.PersistenceDirectoryService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

@Component("persistenceDirectoryService")
public class PersistenceDirectoryServiceImpl implements PersistenceDirectoryService {

	private String root;
	private Properties props;
	private static final String FILE_SEPARATOR = "/";

	@Value("classpath:system-config.properties")
	private Resource resource;
	
	/**
	 * 读取配置信息,初始化root路径
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	private void initialize() throws IOException {
		
		Assert.notNull(resource);
		
		props = PropertiesLoaderUtils.loadProperties(resource);
		
		root = props.getProperty(Constant.MOVIE_UPLOAD_ROOT_PATH_CONSTANT);
	}
	
	/**
	 * 生成持久化文件路径
	 * 
	 * @param	originalFileName	原文件名
	 * @param	pathProperties		配置文件中该文件存储前缀路径的属性名
	 * @return 	String				生成的持久化文件路径
	 * @throws	ServiceException
	 */
	@Override
	public String generatePersistenceFilePath(String originalFileName, String pathProperties) throws ServiceException {
		
		try
		{
			String basePath = (String) props.getProperty(pathProperties);
			
			String persistenceDirectory = generatePersistenceDirectory(basePath);
			
			FileTypes fileType = FileTypes.obtainFileType(originalFileName);
			
			StringBuilder sb = new StringBuilder(persistenceDirectory).append(FILE_SEPARATOR)
				.append(UUID.randomUUID()).append(fileType.getSuffix());

			return sb.toString();
		}
		catch (IOException e) 
		{
			throw new ServiceException("生成临时目录失败", e);
		} 
	}
	
	/**
	 * 根据持久化文件路径(生成的持久化文件路径)获取持久化目录中的相对root的文件地址(截断路径)
	 * 
	 * @param 	persistenceFilePath		持久化文件路径(生成的持久化文件路径)
	 * @return 	String					持久化目录中的相对root的文件地址(截断路径)
	 * @throws 	ServiceException
	 */
	@Override
	public String truncatePersistenceFilePath(String persistenceFilePath) throws ServiceException {
		
		if (persistenceFilePath.startsWith(root))
		{
			persistenceFilePath = persistenceFilePath.substring(root.length());
		}
		
		return persistenceFilePath;
	}
	
	/**
	 * 根据持久化文件路径(数据库中存储的路径)获取持久化目录中的文件地址
	 * 
	 * @param 	persistenceFilePath		持久化文件路径(数据库中存储的路径)
	 * @return 	String					持久化目录中的文件地址
	 * @throws 	ServiceException
	 */
	@Override
	public String obtainPersistenceFilePath(String persistenceFilePath) throws ServiceException {
		
		if (persistenceFilePath.startsWith(root))
		{
			persistenceFilePath = persistenceFilePath.substring(root.length());
		}
		
		return root + persistenceFilePath;
	}
	
	/**
	 * 生成持久化目录路径
	 * 
	 * @param	basePath		生成的持久化目录路径前缀
	 * @return	String 			生成的持久化目录路径
	 * @throws 	IOException
	 */
	private String generatePersistenceDirectory(String basePath) throws IOException {
		
		Assert.notNull(root);
		
		String timestamp = CodeHelper.dateToString(new Date(), "yyyy/MM/dd");

		StringBuilder sb = new StringBuilder(root).append(basePath)
				.append(FILE_SEPARATOR).append(timestamp);
		
		return sb.toString();
	}

}
