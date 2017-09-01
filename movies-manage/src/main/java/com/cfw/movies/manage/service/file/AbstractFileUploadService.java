package com.cfw.movies.manage.service.file;

import com.cfw.movies.commons.exception.FileHouseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractFileUploadService implements FileUploadService {

	/**
	 * 上传文件
	 * 
	 * @param	source					原文件名
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	@Override
	public void upload(String source, String output) throws FileHouseException {

		try
		{
			write(new FileInputStream(source), output);
		}
		catch (IOException e)
		{
			throw new FileHouseException("上传失败", e);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param	source					原文件
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	@Override
	public void upload(File source, String output) throws FileHouseException {

		try 
		{
			write(new FileInputStream(source), output);
		} 
		catch (IOException e) 
		{
			throw new FileHouseException("上传失败", e);
		}
	}

	/**
	 * 上传文件
	 * 
	 * @param	source					原文件输入流
	 * @param	output					目标文件名
	 * @throws	FileHouseException
	 */
	@Override
	public void upload(InputStream source, String output) throws FileHouseException {

		try 
		{
			write(source, output);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			throw new FileHouseException("上传失败", e);
		}
	}

	/**
	 * 将输入流写出到目标文件
	 * 
	 * @param source					原文件输入流
	 * @param output					目标文件名
	 * @throws IOException
	 */
	protected abstract void write(InputStream source, String output) throws IOException;

}
