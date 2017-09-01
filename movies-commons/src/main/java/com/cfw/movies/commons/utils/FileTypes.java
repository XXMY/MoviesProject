package com.cfw.movies.commons.utils;


import com.cfw.movies.commons.exception.UnknownFileTypeException;

public enum FileTypes {

	BMP("bmp"), GIF("gif"), JPEG("jpeg"), JPG("jpg"), PNG("png");

	private String value;

	private FileTypes(String value) {
		this.value = value;
	}

	/**
	 * 获取该文件类型的类型名
	 * e.g.	JPG --> jpg
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 获取该文件类型的后缀名
	 * e.g.	JPG --> .jpg
	 */
	public String getSuffix() {
		return "." + value;
	}

	/**
	 * 根据文件名或者文件路径获取文件类型枚举对象
	 * 
	 * @param fileName	文件名或文件路径
	 */
	public static FileTypes obtainFileType(String fileName) throws UnknownFileTypeException {
		
		try
		{
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			return FileTypes.valueOf(suffix.toUpperCase());
		}
		catch (IllegalArgumentException e)
		{
			throw new UnknownFileTypeException("不支持的文件类型", e);
		}
		
	}
	
}
