package com.cfw.movies.commons.utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	/**
	 * 截断原文件路径(相对路径或绝对路径)获取该文件路径下的文件名
	 * e.g.		D:/test/test.txt --> test.txt
	 * 
	 * @param 	fileName	原文件路径
	 * @return 	String		截断后的文件名
	 */
	public static String truncateFileName(String fileName) {
		
		return new File(fileName).getName();
	}
	
	/**
	 * 截断原文件路径(相对路径或绝对路径)获取该文件路径下所在目录路径
	 * e.g.		D:/test/test.txt --> D:/test
	 * 
	 * @param 	fileName	原文件路径
	 * @return 	String 		截断后的目录路径
	 */
	public static String truncateFilePath(String fileName) {
		
		return new File(fileName).getParent();
	}
	
	/**
	 * 判断文件路径所对应的文件夹是否存在,不存在则创建文件夹
	 * 
	 * @param 	directory	文件路径
	 * @throws 	IOException
	 */
	public static void createDirectory(String directory) throws IOException {
		
		createDirectory(new File(directory));
	}
	
	/**
	 * 判断文件路径所对应的文件夹是否存在,不存在则创建文件夹
	 * 
	 * @param 	directory	目录文件
	 * @throws 	IOException
	 */
	public static void createDirectory(File directory) throws IOException {
		
		if (!directory.exists())
		{
			directory.mkdirs();
		}
	}
	
}
