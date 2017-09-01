package com.cfw.movies.manage.service.impl;

import com.cfw.movies.commons.exception.ServiceException;
import com.cfw.movies.commons.utils.CodeHelper;
import com.cfw.movies.commons.utils.FileTypes;
import com.cfw.movies.commons.utils.FileUtils;
import com.cfw.movies.manage.service.TempDirectoryService;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Component("tempDirectoryManager")
public class TempDirectoryServiceImpl implements TempDirectoryService {

	@Autowired
	private ServletContext servletContext;

	private String root;
	private static final String TEMP_DIRECTORY_NAME = "temp";
	private static final String FILE_SEPARATOR = File.separator;

	/**
	 * 读取配置信息,初始化root路径,若root目录不存在,则同时创建该目录文件夹
	 * 
	 * @throws IOException
	 */
	@PostConstruct
	private void initialize() throws IOException {
		
		Assert.notNull(servletContext);
		
		root = servletContext.getRealPath("/") + TEMP_DIRECTORY_NAME;
		//root = Constants.TEMP_PATH;
		
		FileUtils.createDirectory(root);
	}
	
	/**
	 * 生成临时文件路径
	 * 
	 * @param 	originalFileName	原文件名
	 * @return 	String				生成的临时文件路径
	 * @throws 	ServiceException
	 */
	@Override
	public String generateTempFilePath(String originalFileName) throws ServiceException {

		try
		{
			String tempDirectory = generateTempDirectory();
			
			String timestamp = FileUtils.truncateFileName(tempDirectory);
			
			FileTypes fileType = FileTypes.obtainFileType(originalFileName);
			
			StringBuilder sb = new StringBuilder(tempDirectory)
					.append(FILE_SEPARATOR).append(timestamp).append("-")
					.append(UUID.randomUUID()).append(fileType.getSuffix());
			
			return sb.toString();
		}
		catch (IOException e) 
		{
			throw new ServiceException("生成临时路径失败", e);
		} 
		
	}
	
	/**
	 * 根据临时文件名获取临时目录中的文件地址
	 * 
	 * @param 	tempFileName		临时文件名
	 * @return 	String				临时目录中的文件地址
	 * @throws 	ServiceException
	 */
	@Override
	public String obtainTempFilePath(String tempFileName) throws ServiceException {
		
		String timestamp = tempFileName.substring(0, tempFileName.indexOf("-"));
		
		String tempDirectory = obtainTempDirectory(timestamp);
		
		StringBuilder sb = new StringBuilder(tempDirectory)
			.append(FILE_SEPARATOR).append(tempFileName);
		
		return sb.toString();
	}
	
	/**
	 * 清除临时目录(只保留最近两天的文件夹)
	 * 
	 * @throws 	ServiceException
	 */
	@Override
	public void cleanTempDirectory() throws ServiceException {
		
		try 
		{
			Date now = new Date();
			String today = CodeHelper.dateToString(now , "yyyyMMdd");
			String yesterday = CodeHelper.dateToString(DateUtils.addDays(now, -1) , "yyyyMMdd");
			
			File rootDirectory = new File(root);
			
			Collection<File> dirs = org.apache.commons.io.FileUtils.listFilesAndDirs(rootDirectory, FalseFileFilter.INSTANCE, DirectoryFileFilter.INSTANCE);
			
			for (File dir : dirs) 
			{
				if (!rootDirectory.equals(dir))
				{
					String dirName = dir.getName();
					
					if (!dirName.equals(today) && !dirName.equals(yesterday))
					{
						org.apache.commons.io.FileUtils.deleteDirectory(dir);
					}
				}
			}
		} 
		catch (IOException e) 
		{
			throw new ServiceException("删除临时目录失败", e);
		} 
		
	}
	
	/**
	 * 生成临时目录路径,若该目录不存在,则同时创建该目录文件夹
	 * 
	 * @return	String 			生成的临时目录路径
	 * @throws 	IOException
	 */
	private String generateTempDirectory() throws IOException {
		
		Assert.notNull(root);
		
		String timestamp = CodeHelper.dateToString(new Date(), "yyyyMMdd");

		StringBuilder sb = new StringBuilder(root).append(FILE_SEPARATOR).append(timestamp);
		
		String tempDirectory = sb.toString();
		
		FileUtils.createDirectory(tempDirectory);
		
		return tempDirectory;
	}

	
	/**
	 * 根据时间戳获取当前临时目录名
	 * 
	 * @param 	timestamp		时间戳
	 * @throws 	IOException
	 */
	private String obtainTempDirectory(String timestamp) {
		
		Assert.notNull(root);
		
		StringBuilder sb = new StringBuilder(root).append(FILE_SEPARATOR).append(timestamp);
		
		return sb.toString();
	}

}
