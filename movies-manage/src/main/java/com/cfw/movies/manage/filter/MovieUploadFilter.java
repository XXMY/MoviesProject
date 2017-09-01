package com.cfw.movies.manage.filter;

import com.cfw.movies.commons.dto.MovieSubmit;
import com.cfw.movies.commons.exception.ServiceException;
import com.cfw.movies.manage.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Movie upload filter to pack the template file name to persistent.
 * @author Fangwei_Cai
 * @time since 2016年4月10日 上午11:06:18
 */
@WebFilter(urlPatterns = {"/Manage/movie/submit"})
public class MovieUploadFilter implements Filter {
	private Log logger = LogFactory.getLog(MovieUploadFilter.class);

	@Autowired
	private UploadService uploadService;
	/*
	public void setUploadService(UploadService uploadService) {
		this.uploadService = uploadService;
	}
	*/
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Main function.
	 * Get the template file name from main picture and description,
	 * use file upload service to persistent it, then replace them to
	 * persistent path and name, finally redirect the request to movie
	 * upload controller.
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * @author Fangwei_Cai
	 * @time since 2016年4月10日 上午11:19:21
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String[] result = null;
		String name = request.getParameter("name");
		String abstract_ = request.getParameter("abstract_");

		MovieSubmit movieSubmit = null;

		try {
			result = this.persistAndReplace(request);

			movieSubmit = new MovieSubmit();
			movieSubmit.setMainPicture(result[0]);
			movieSubmit.setAbstract_(abstract_);
			movieSubmit.setDescription(result[1]);
			movieSubmit.setName(name);
		} catch (ServiceException e) {
			this.logger.error(e.getMessage(),e);
		}

		request.setAttribute("movieSubmit", movieSubmit);
		
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}
	
	/**
	 * This function to get template file URI and persistent the 
	 * template file in it.
	 * @return {String []}, Persisted URI of main picture and those
	 * in description.
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午10:19:52
	 */
	private String [] persistAndReplace(HttpServletRequest request) throws ServiceException {
		
		String mainPicTempName = request.getParameter("tempFilePath");
		String description = request.getParameter("description");

		if(mainPicTempName == null || description == null )
			throw new ServiceException("tempFilePath or description null");

		String regex = "http://www.m1212.com/temp/.{54}\\.((jpg)|(png))";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(description);
		
		String tempFileName = mainPicTempName;
		
		String mainPicture = "";

		if(StringUtils.isNotEmpty(tempFileName))
			mainPicture = uploadService.persistTempFile(tempFileName, "movie.upload.movie.path");

		while(matcher.find()){
			String [] tempFilePathArray = matcher.group().split("/");
			tempFileName = tempFilePathArray[tempFilePathArray.length-1];
			String persistentFilePath = uploadService.persistTempFile(tempFileName, "movie.upload.movie.path");
			description = matcher.replaceFirst(persistentFilePath);
			matcher = pattern.matcher(description);
		}
		
		return new String[]{mainPicture,description};
	}

}
