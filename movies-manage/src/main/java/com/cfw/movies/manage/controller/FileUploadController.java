package com.cfw.movies.manage.controller;

import com.cfw.movies.commons.dto.UploadResult;
import com.cfw.movies.commons.exception.ServiceException;
import com.cfw.movies.commons.utils.Folder;
import com.cfw.movies.manage.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年3月21日 上午10:33:53
 */
@RequestMapping("/Manage/upload")
@Controller
public class FileUploadController {
	
	@Autowired
	private UploadService uploadServiceImpl;
	
	/**
	 * This function upload files to the directory is 
	 * E:/MyCode/Java/MovieSource/upload/temp
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午2:58:38
	 */
	@RequestMapping("/temp")
	@ResponseBody
	public UploadResult uploadTempFiles(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
		UploadResult uploadResult = new UploadResult();
		
		String tempPath = Folder.tempUploadDir();
		
		String tempFilePathName = tempPath + file.getOriginalFilename();
		
		System.out.println(tempFilePathName);
		try{
			file.transferTo(new File(tempFilePathName));
			uploadResult.setTempFileName(tempFilePathName);
			uploadResult.setLink(Folder.templinkPath() + file.getOriginalFilename());
			uploadResult.setStatus((short)1);
			uploadResult.setMessage("文件上传成功");
		}catch(Exception e){
			e.printStackTrace();
			uploadException(uploadResult);
		}
		
		
		return uploadResult;
	}
	
	
	/**
	 * Upload Files to template directory of tomcat.
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午2:52:25
	 */
	@RequestMapping("/temp2")
	@ResponseBody
	public UploadResult uploadTemp2Files(@RequestParam("file") MultipartFile file, HttpServletRequest request){
		UploadResult uploadResult = new UploadResult();
		try{
			String tempFileName = this.uploadServiceImpl.uploadTempFile(file.getInputStream(), file.getOriginalFilename());
			uploadResult.setTempFileName(tempFileName);
			uploadResult.setLink(Folder.templinkPath() + tempFileName);
			uploadResult.setStatus((short)1);
			uploadResult.setMessage("文件上传成功");
		}catch(ServiceException se){
			se.printStackTrace();
			uploadException(uploadResult);
		}catch(IOException ioe){
			ioe.printStackTrace();
			uploadException(uploadResult);
		}
		
		return uploadResult;
	}
	
	/**
	 * Upload single file.
	 * While uploading single file, in general there will have some another informations take with.<br>
	 * This function just upload the file itself and turn to real controller in path parameter<br>
	 * to save the left informations.
	 * <p>File upload function while set exception attribute into request that the real controller can get. <br>
	 * The exception's attribute name is "fileUploadException"
	 * @author CaiFangwei
	 * @time since 2015年11月10日 上午11:40:33
	 */
	@RequestMapping("/pstFile")
	public String uploadPersistentFile(HttpServletRequest request, MultipartFile file,@RequestParam("pathType") short type, String path){
		String persistentPath = this.persistentFilePath(type);
		String persistentFilePathName = "";
		try{
			persistentFilePathName = this.uploadServiceImpl.uploadPersistenceFile(file.getInputStream(), file.getOriginalFilename(), persistentPath);
		}catch(ServiceException se){
			se.printStackTrace();
			this.uploadException(request);
		}catch(IOException ioe){
			ioe.printStackTrace();
			this.uploadException(request);
		}finally{
			request.setAttribute("persistentFilePathName", persistentFilePathName);
		}
		
		return "forward:/"+path;
	}
	
	/**
	 * Upload multiple files from temporary location to persistent location.
	 * @author CaiFangwei
	 * @time since 2015年11月10日 上午11:40:46
	 */
	@RequestMapping("/pstFiles")
	public String uploadPersistFiles(HttpServletRequest request, @RequestParam("pathType") short type, String path,String... tempFilePath){
		List<String> persistentFilePathNames = new ArrayList<String>();
		String persistentPath = this.persistentFilePath(type);
		try{
			for(String filePathName : tempFilePath){
				String filePath = this.uploadServiceImpl.persistTempFile(filePathName, persistentPath);
				persistentFilePathNames.add(filePath);
			}
		}catch(ServiceException se){
			se.printStackTrace();
			request.setAttribute("fileUploadException", true);
		}finally{
			request.setAttribute("persistentFilePathNames", persistentFilePathNames);
		}
		
		return "forward:/"+path;
		
	}
	
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午2:55:50
	 */
	private String persistentFilePath(short type){
		String persistentPath = "";
		switch(type){
		}
		return persistentPath;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午2:55:57
	 */
	private void uploadException(UploadResult uploadResult){
		uploadResult.setStatus((short)0);
		uploadResult.setMessage("文件上传异常");
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午2:56:32
	 */
	private void uploadException(HttpServletRequest request){
		request.setAttribute("fileUploadException", true);
	}
}
