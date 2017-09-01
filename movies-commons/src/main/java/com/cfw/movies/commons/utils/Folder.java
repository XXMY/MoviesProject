package com.cfw.movies.commons.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.cfw.movies.commons.utils.Constants.TEMP_LINK_PATH;
import static com.cfw.movies.commons.utils.Constants.TEMP_PATH;

/**
 * Folder management.
 * @author Fangwei_Cai
 * @time since 2016年4月3日 上午11:15:59
 */
public class Folder {
	private static Calendar calendar = Calendar.getInstance();
	
	/**
	 * Check whether the folder path exists or not.
	 * If it is not exist, then create it.
	 * @author Fangwei_Cai
	 * @throws IOException 
	 * @time since 2016年4月3日 上午11:21:30
	 */
	public static boolean checkFolder(String folderPath) throws IOException {
		File folder = new File(folderPath);
		if(!folder.exists()){
			boolean result = folder.mkdir();
					
			return result;
		}
		return true;
	}
	
	/**
	 * Gain the directory of current date.
	 * @author Fangwei_Cai
	 * @time since 2016年4月3日 上午11:23:04
	 */
	public static int [] todayDir() {
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		
		int [] array = new int[3];
		array[0] = year;
		array[1] = month;
		array[2] = day;
		return array;
	}
	
	/**
	 * Return the template file upload directory.
	 * @author Fangwei_Cai
	 * @throws IOException 
	 * @time since 2016年4月3日 上午11:34:34
	 */
	public static String tempUploadDir() throws IOException{
		
		//String tempUploadDir = TEMP_PATH + todayDir();
		String tempUploadDir = TEMP_PATH;
		int [] array = todayDir();
		for(int dir : array){
			tempUploadDir += dir +"/";
			boolean result = checkFolder(tempUploadDir);
			if(!result){
				return TEMP_PATH;
			}
		}
		
		return tempUploadDir;
		
	}
	
	/**
	 * Return the template uploaded picture URI.
	 * @author Fangwei_Cai
	 * @time since 2016年4月4日 上午7:59:33
	 */
	public static String templinkPath() {
		String tempLinkPath = TEMP_LINK_PATH;
		String timestamp = CodeHelper.dateToString(new Date(), "yyyyMMdd");
		tempLinkPath += timestamp +"/";
		
		return tempLinkPath;
		
	}
}
