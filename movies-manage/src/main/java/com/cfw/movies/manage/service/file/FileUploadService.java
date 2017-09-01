package com.cfw.movies.manage.service.file;

import com.cfw.movies.commons.exception.FileHouseException;

import java.io.File;
import java.io.InputStream;

public interface FileUploadService extends FileHouseService {

	/**
	 * 上传文件
	 * 
	 * @param	source					原文件名
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	public void upload(String source, String output) throws FileHouseException;
	
	/**
	 * 上传文件
	 * 
	 * @param	source					原文件
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	public void upload(File source, String output) throws FileHouseException;
	
	/**
	 * 上传文件
	 * 
	 * @param	source					原文件输入流
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	public void upload(InputStream source, String output) throws FileHouseException;
	
}
