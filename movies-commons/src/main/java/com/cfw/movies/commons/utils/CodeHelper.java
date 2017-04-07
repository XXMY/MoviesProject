package com.cfw.movies.commons.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * <编写代码时提供简单逻辑判断控制的函数类> <功能详细描述>
 * 
 * @author qiaochunguang
 * @version [版本号, Nov 18, 2011]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CodeHelper {
	/**
	 * 图片服务器路径
	 */
	private static String IMAGESERVER=null;
	
	private static String WEBSERVERPATH = null;
	
	private static final String LINUX_CONFIG_FILE_PATH = "/system-config-linux.properties";
	private static final String WINDOWS_CONFIG_FILE_PATH = "/system-config.properties";
	public static final String UPLOAD_IMG_MAXSIZE_HEIGHT = "upload.img.maxsize.height";
	public static final String UPLOAD_IMG_MAXSIZE_WIDTH = "upload.img.maxsize.width";
    public static final int UPLOAD_IMG_DEFAULTSIZE_HEIGHT = 442;
    public static final int UPLOAD_IMG_DEFAULTSIZE_WIDTH = 442;
    public static final String UPLOAD_IMG_MAXSIZE_FILESIZE = "upload.img.maxsize.filesize";
    public static final String UPLOAD_IMG_UPLOADPATH = "upload.img.uploadpath";
	
	public static String removeNull(String str) {
		return str != null ? str : "";
	}

	public static boolean isEmpty(String string) {
		return (string == null) || (string.length() == 0);
	}

	public static boolean isEmpty(Object[] array) {
		return CodeHelper.isNull(array) || array.length == 0;
	}
	
	/**
	 * 对象是否为NULL
	 * 
	 * @param param
	 *            Object
	 * @return Null:TRUE ; NotNull : FALSE
	 */
	public static boolean isNull(Object param) {
		return null == param;
	}

	/**
	 * 对象是否不为NULL
	 * 
	 * @param param
	 *            Object
	 * @return Null:FALSE ; NotNull : TRUE
	 */
	public static boolean isNotNull(Object param) {
		return null != param;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param param
	 *            String
	 * @return TRUE:Null Or Empty ; FALSE:length > 0
	 */
	public static boolean isNullOrEmpty(String param) {
		return null == param || param.trim().length() == 0;
	}

	/**
	 * 判断集合是否为空
	 * 
	 * @param param
	 *            Collection
	 * @return TRUE:Null Or Empty ; FALSE:size > 0
	 */
	public static boolean isNullOrEmpty(Collection<? extends Object> param) {
		return null == param || param.isEmpty();
	}
	
	/*********************************************************************************
	 * 
	 * @USE: to judge a string from js is null or not
	 * 
	 * @PARAM: source: String to judge
	 * 
	 * @RETURN: if source String is null or empty or equals null or equals undefined 
	 *         after being trimed, return true; otherwise, return false
	 * @FOR: to judge a String from js is null or not
	 * 
	 * @AUTHOR: Zhaoya
	 * @DATE: 2014.12.08.17.30
	 * @DETAILS: ...
	 * 
	 */
	public static boolean isNullOrEmptySourceJs(String source) {
	    return (isNullOrEmpty(source)||"null".equalsIgnoreCase(source.trim())||"undefined".equalsIgnoreCase(source.trim()));
	}
	
    /*********************************************************************************
     * 
     * @USE: to judge a string from js if it is not null
     * 
     * @PARAM: source: String to judge
     * 
     * @RETURN: if source String is null or empty or equals null or equals undefined 
     *         after being trimed, return false; otherwise, return true
     * @FOR: to judge a String from js if it is not null
     * 
     * @AUTHOR: Zhaoya
     * @DATE: 2014.12.08.17.30
     * @DETAILS: ...
     * 
     */
    public static boolean isNotNullOrEmptySourceJs(String source) {
        return !isNullOrEmptySourceJs(source);
    }

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param param
	 *            String
	 * @return TRUE:length > 0 ; FALSE:Null Or Empty
	 */
	public static boolean isNotEmpty(String param) {
		return null != param && param.trim().length() > 0;
	}

	/**
	 * 判断集合是否不为空
	 * 
	 * @param param
	 *            Collection
	 * @return TRUE:size > 0 ; FALSE:Null Or Empty
	 */
	public static boolean isNotEmpty(Collection<? extends Object> param) {
		return null != param && !param.isEmpty();
	}

	public static List<Object> convertMapToList(
			Map<? extends Object, ? extends Object> map) {
		List<Object> list = new ArrayList<Object>();
		if (CodeHelper.isNotEmpty(map)) {
			Iterator<? extends Object> iterator = map.values().iterator();
			while (iterator.hasNext()) {
				Object o = iterator.next();
				list.add(o);
			}
		}
		return list;
	}

	public static boolean isNotEmpty(Map<? extends Object, ? extends Object> map) {
		return null != map && !map.isEmpty();
	}

	public static boolean isNotEmpty(Integer integer) {
		return CodeHelper.isNotNull(integer) && integer.intValue() >=0;
	}

	public static boolean isNotEmpty(Object[] array) {
		return CodeHelper.isNotNull(array) && array.length > 0;
	}

	public static boolean checkEventObject(EventObject eventObject) {
		if (eventObject == null || eventObject.getSource() == null) {
			return false;
		}
		return true;
	}

	
	/*********************************************************************************
	 * 
	 * @USE: create a new file naming by random
	 * 
	 * @PARAM: path: file creating path(like:  e:/pic)
	 * @PARAM: suffixName: file's suffix name(like: .jpg)
	 * 
	 * @RETURN: a new file
	 * @FOR: create a new file with random name
	 * 
	 * @AUTHOR: Zhaoya
	 * @DATE: 2014.12.19.11.40
	 * @DETAILS: ...
	 * 
	 */
	public static File createNewFile(String path, String suffixName) {
	    File file = null;
	    if(isNullOrEmpty(path)) {
	        path = "";
	    }
	    
	    while(true) {
	        file = new File(path + File.separator + System.currentTimeMillis()+(int)(Math.random()*Math.round(999.99))+ suffixName);
	        if(file.exists()) {
	            continue;
	        } else {
	            break;
	        }
	    }
        try {
            file.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    
	    return file;
	}
	
	

	/**
	 * 时间类型转成字符串类型
	 * 
	 * @param time
	 * @param format
	 * @return
	 */
	public static String dateToString(Date time, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(time);
	}
	
	/**
	 * 字符串类型转换成时间类型
	 * 
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String time, String format)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(time);
	}
	
	/**
	 * 获取客户端Ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {     
        String ip = request.getHeader("x-forwarded-for");     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("Proxy-Client-IP");     
        }     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getHeader("WL-Proxy-Client-IP");     
        }     
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {     
            ip = request.getRemoteAddr();     
        }     
        return ip;     
    }  
	
	/**
	 * 如何获取字符串的字节数！
	 * @param value
	 * @return
	 */
	public static int getByteLength(String value){
		int c=0;
		for (int i = 0; i < value.length(); i++) {
			String st = value.charAt(i) + "";
			c++;
			// 一个汉字的字节长度
			if (st.getBytes().length == 3 || st.getBytes().length == 2) {
				c++; // 自动加1
			} 
		}
		return c;
	}
	
	
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUId(){
		return UUID.randomUUID().toString();
	}
	
	
	/**
	 * 检查url文件夹路径是否存在，如果不存在，则创建
	 * @param url
	 */
	public static void createFileDic(ServletContext context,String url){
		File dic = new File(context.getRealPath("/"),url);
		if(!dic.exists()){
			dic.mkdirs();
		}
	}
	
	/**
	 * 根据传入的年份获取前四年与后九年
	 * @param year
	 * @return
	 */
	public static List<String> getYearsBySelectYear(String year){
		if(year==null||"".equals(year)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
			year = sdf.format(new Date()).substring(0, 4);
		}
		
		List<String> rtList = new ArrayList<String>();
		
		Integer tmpYear = Integer.parseInt(year);
		Integer tmp = 0;
		for(int i=4;i>0;i--){
			tmp = tmpYear+i;
			rtList.add(tmp.toString());
			
		}
		
		rtList.add(year);
		
		tmp = Integer.parseInt(year);
		for(int i=1;i<10;i++){
			tmp = tmpYear-i;
			rtList.add(tmp.toString());
		}
		
		return rtList;
	}
	
	public static String htmlEncode(String str){
		if(str==null){
			return "";
		}
         String s = str.replace("&", "&amp;");   
		  s = s.replace("<", "&lt;");   
		  s = s.replace(">", "&gt;");   
		  s = s.replace(" ", "&nbsp;");   
		  s = s.replace("'", "&#39;");   
		  s = s.replace("\"", "&quot;");   
		  s = s.replace("\n", "<br>");  
		return s;
	}
	public static String htmlEncodeBack(String str){
		if(str==null){
			return "";
		}
         String s = str.replace("&lt;", "<");   
		  s = s.replace("&gt;",">");   
		  s = s.replace("&nbsp;"," ");   
		  s = s.replace("&#39;","'");   
		  s = s.replace("&quot;","\"");   
		  s = s.replace("<br>","\n");  
		  s = s.replace("&amp;", "&");   
		return s;
	}
	/**
	 * double 类型保留小数点
	 * d 传入数值 s 格式化几位：一位#.#
	 */
	public static String doubleFormat(double d,String s) {
		DecimalFormat   df   =   new   DecimalFormat(s);
		return df.format(d);
	}

	
	/**
	 * 临时保存地址
	 * @author chenxiaoyue
	 * @date 2014-4-23
	 * @return
	 */
	private static String TEST_IMAGE_TEMPORARY_RUL = null;
	public static String getTemporaryPath(HttpServletRequest request){
		if(TEST_IMAGE_TEMPORARY_RUL==null){
			TEST_IMAGE_TEMPORARY_RUL = request.getSession().getServletContext().getRealPath("/")+ "temporaryImage";
		}
		return TEST_IMAGE_TEMPORARY_RUL;
	}
	
	/**
     * 将字符串编码成 Unicode 形式的字符串. 如 "黄" to "\u9EC4"

     * Converts unicodes to encoded \\uxxxx and escapes

     * special characters with a preceding slash

     *

     * @param theString

     *        待转换成Unicode编码的字符串。

     * @param escapeSpace

     *        是否忽略空格，为true时在空格后面是否加个反斜杠。

     * @return 返回转换后Unicode编码的字符串。

     */

    public static String toEncodedUnicode(String theString, boolean escapeSpace) {

        int len = theString.length();

        int bufLen = len * 2;

        if (bufLen < 0) {

            bufLen = Integer.MAX_VALUE;

        }

        StringBuffer outBuffer = new StringBuffer(bufLen);

 


        for (int x = 0; x < len; x++) {

            char aChar = theString.charAt(x);

            // Handle common case first, selecting largest block that

            // avoids the specials below

            if ((aChar > 61) && (aChar < 127)) {

                if (aChar == '\\') {

                    outBuffer.append('\\');

                    outBuffer.append('\\');

                    continue;

                }

                outBuffer.append(aChar);

                continue;

            }

           

            switch (aChar) {

            case ' ':

                if (x == 0 || escapeSpace) outBuffer.append('\\');

                outBuffer.append(' ');

                break;

            case '\t':

                outBuffer.append('\\');

                outBuffer.append('t');

                break;

            case '\n':

                outBuffer.append('\\');

                outBuffer.append('n');

                break;

            case '\r':

                outBuffer.append('\\');

                outBuffer.append('r');

                break;

            case '\f':

                outBuffer.append('\\');

                outBuffer.append('f');

                break;

            case '=': // Fall through

            case ':': // Fall through

            case '#': // Fall through

            case '!':

                outBuffer.append('\\');

                outBuffer.append(aChar);

                break;

            default:

                if ((aChar < 0x0020) || (aChar > 0x007e)) {

                    // 每个unicode有16位，每四位对应的16进制从高位保存到低位

                    outBuffer.append('\\');

                    outBuffer.append('u');

                    outBuffer.append(toHex((aChar >> 12) & 0xF));

                    outBuffer.append(toHex((aChar >> 8) & 0xF));

                    outBuffer.append(toHex((aChar >> 4) & 0xF));

                    outBuffer.append(toHex(aChar & 0xF));

                } else {

                    outBuffer.append(aChar);

                }

            }

        }

        return outBuffer.toString();

    }
    private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',

        'B', 'C', 'D', 'E', 'F' };

	private static char toHex(int nibble) {
	
	    return hexDigit[(nibble & 0xF)];
	
	}
 
    /**

     * 从 Unicode 形式的字符串转换成对应的编码的特殊字符串。 如 "\u9EC4" to "黄".

     * Converts encoded \\uxxxx to unicode chars

     * and changes special saved chars to their original forms

     *

     * @param in

     *        Unicode编码的字符数组。

     * @param off

     *        转换的起始偏移量。

     * @param len

     *        转换的字符长度。

     * @param convtBuf

     *        转换的缓存字符数组。

     * @return 完成转换，返回编码前的特殊字符串。

     */
    public static String fromEncodedUnicode(String s) {
    	char[] in =s.toCharArray();int off=0;int len=s.length();
        char aChar;

        char[] out = new char[len]; // 只短不长

        int outLen = 0;

        int end = off + len;

 


        while (off < end) {

            aChar = in[off++];

            if (aChar == '\\') {

                aChar = in[off++];

                if (aChar == 'u') {

                    // Read the xxxx

                    int value = 0;

                    for (int i = 0; i < 4; i++) {

                        aChar = in[off++];

                        switch (aChar) {

                        case '0':

                        case '1':

                        case '2':

                        case '3':

                        case '4':

                        case '5':

                        case '6':

                        case '7':

                        case '8':

                        case '9':

                            value = (value << 4) + aChar - '0';

                            break;

                        case 'a':

                        case 'b':

                        case 'c':

                        case 'd':

                        case 'e':

                        case 'f':

                            value = (value << 4) + 10 + aChar - 'a';

                            break;

                        case 'A':

                        case 'B':

                        case 'C':

                        case 'D':

                        case 'E':

                        case 'F':

                            value = (value << 4) + 10 + aChar - 'A';

                            break;

                        default:

                            throw new IllegalArgumentException("Malformed \\uxxxx encoding.");

                        }

                    }

                    out[outLen++] = (char) value;

                } else {

                    if (aChar == 't') {

                        aChar = '\t';

                    } else if (aChar == 'r') {

                        aChar = '\r';

                    } else if (aChar == 'n') {

                        aChar = '\n';

                    } else if (aChar == 'f') {

                        aChar = '\f';

                    }

                    out[outLen++] = aChar;

                }

            } else {

                out[outLen++] = (char) aChar;

            }

        }

        return new String(out, 0, outLen);

    }
    
    
    
    /*********************************************************************************
     * 
     * @USE: get filename's suffix file name
     * 
     * @PARAM: fileName: a file name
     * 
     * @RETURN: file name's suffix name
     * @FOR: get a file's suffix file name
     * 
     * @AUTHOR: Zhaoya
     * @DATE: 2014.12.18.19.47
     * @DETAILS: ...
     * 
     */
    public static String getFileSuffixName(String fileName) {
        if(CodeHelper.isNullOrEmpty(fileName)) {
            return null;
        }
        
        return fileName.substring(fileName.lastIndexOf("."));
    }
    
    /**

     * 使用文件通道的方式复制文件

     * 

     * @param s

     *            源文件

     * @param t

     *            复制到的新文件

     */

     public static void fileChannelCopy(File s, File t) {

         FileInputStream fi = null;

         FileOutputStream fo = null;

         FileChannel in = null;

         FileChannel out = null;

         try {

             fi = new FileInputStream(s);

             fo = new FileOutputStream(t);

             in = fi.getChannel();//得到对应的文件通道

             out = fo.getChannel();//得到对应的文件通道

             in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道

         } catch (IOException e) {

             e.printStackTrace();

         } finally {

             try {
            	 if(fi!=null)
                 fi.close();
            	 if(in!=null)
                 in.close();
            	 if(fo!=null)
                 fo.close();
            	 if(out!=null)
                 out.close();

             } catch (IOException e) {

                 e.printStackTrace();

             }

         }

     }
}