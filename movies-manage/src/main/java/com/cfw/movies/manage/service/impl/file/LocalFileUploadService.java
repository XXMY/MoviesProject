package com.cfw.movies.manage.service.impl.file;

import com.cfw.movies.manage.service.file.AbstractFileUploadService;
import com.cfw.movies.manage.service.file.FileUploadService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Component("fileUploadHandler")
public class LocalFileUploadService extends AbstractFileUploadService implements FileUploadService {

	/**
	 * 将输入流写出到本地目标文件
	 * 
	 * @param source					原文件输入流
	 * @param output					目标文件名
	 * @throws IOException
	 */
	@Override
	protected void write(InputStream source, String output) throws IOException {
		
		com.cfw.movies.commons.utils.FileUtils .createDirectory(com.cfw.movies.commons.utils.FileUtils .truncateFilePath(output));
		
		FileUtils.copyInputStreamToFile(source, new File(output));
	}

}
