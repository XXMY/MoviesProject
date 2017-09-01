package com.cfw.movies.manage.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.dto.MovieSubmit;
import com.cfw.movies.commons.model.Description;
import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.vo.MoviesResponse;
import com.cfw.movies.home.service.MovieService;
import com.cfw.movies.manage.RecommendStatus;
import com.cfw.movies.recommend.service.RecommendService;
import com.cfw.plugins.rmi.annotation.CRmiImport;
import com.cfw.plugins.rmi.annotation.CRmiImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The controller contains movies' operations.
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午3:04:14
 */
@Controller
@RequestMapping("/Manage")
@CRmiImport
public class ManageController extends BaseController {
	
	@Autowired
	@CRmiImportService
	private MovieService movieService;
	
	@Autowired
	@CRmiImportService
	private RecommendService recommendService;
	
	/**
	 * Finish the movie submit operation.
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午10:45:52
	 */
	@RequestMapping(value="/movie/submit",method=RequestMethod.POST)
	@ResponseBody
	public MoviesResponse movieSubmit(HttpServletRequest request, String type, @RequestParam(required=false,defaultValue="0")Integer id){
		MovieSubmit movieSubmit = (MovieSubmit) request.getAttribute("movieSubmit");
		
		Description description = new Description(movieSubmit.getDescription());
		description.setAbstract_(movieSubmit.getAbstract_());
		
		Movie movie = new Movie(movieSubmit.getName(), type, description, movieSubmit.getMainPicture());
		movie.setId(id);
		boolean result = false;
		
		// id not exists means it's a new commit.
		if(id == 0l){
			result = this.movieService.addMovie(movie);
		}else{
			// modification commit.
			result = this.movieService.modifyMoive(movie);
		}
		
		if(result){
			return buildResponse(0, "添加成功");
		}
		
		return buildResponse(1, "添加失败");
		
	}
	
	/**
	 * Delete movies.
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午9:40:49
	 * @param mid
	 * @return
	 */
	@RequestMapping(value="/movie/delete",method=RequestMethod.GET)
	@ResponseBody
	public MoviesResponse movieDelete(Integer mid){
		MoviesResponse response = null;
		
		if(mid == null){
			return buildResponse(0, "参数错误");
		}
		
		boolean deleteResult = false;
		deleteResult = this.movieService.deleteMovie(mid);
		
		if(deleteResult){
			response = buildResponse(1, "删除成功");
		}else{
			response = buildResponse(0, "删除失败");
		}
		
		return response;
	}

	/**
	 * Recommend movies <p>
	 * Client will send request in every five seconds
	 * to fetch the latest state of recommend. 
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 下午8:51:36
	 * @return
	 */
	@RequestMapping("/recmd")
	@ResponseBody
	public MoviesResponse movieRecommend(){
		MoviesResponse response = null;
		
		if(!RecommendStatus.inProcessing){
			this.recommendService.startRecommend();
		}
		
		response = this.recommendService.getRecommendStaus();
		
		if(response.getCode() == 90){
			boolean result = recommendService.processRecommendData();
			if(result){
				response.setCode(100);
				response.setMessage("生成推荐结果完成，推荐结束");
			}else{
				response.setCode(0);
				response.setMessage("生成推荐结果失败");
			}
			
		}
		
		return response;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午9:00:40
	 * @return
	 * @throws IOException
	 */
	//@RequestMapping("/local")
	@ResponseBody
	public String localPicture() throws IOException {
		int num = 0;
		
		Map<String,Object> map = new HashMap<String,Object>();
		Long start = 0L;
		int length = 900;
		
		while(num==0){
			map.clear();
			map.put("start", start);
			map.put("length", length);
			
			List<Movie> movies = this.movieService.findPic(map);
			
			if(movies == null || movies.size()==0){
				return "Finished!";
			}
			
			InputStream input = null;
			FileOutputStream fileOut = null;
			try{
				for(Movie movie : movies){
					System.out.println("movie: "+movie.getId()+", "+movie.getName());
					String picUrl = movie.getPic();
					
					if(!picUrl.startsWith("http")) continue;
					
					URL url = new URL(picUrl);
					HttpURLConnection connection = (HttpURLConnection)url.openConnection();
					
					String contentType = connection.getContentType();
					if(contentType==null || !contentType.equalsIgnoreCase("image/jpeg") && !contentType.equalsIgnoreCase("image/png")){
						System.err.println(movie.getId());
						continue;
					}
					
					int cotentLength = connection.getContentLength();
					input = connection.getInputStream();
					int contentLength = input.available();
					
					if(cotentLength <= 0){
						System.err.println(movie.getId());
						continue;
					}
					byte [] fileByte = new byte[cotentLength];
					
					
					for(int i=0;i<cotentLength;i++){
						fileByte[i] = (byte)input.read();
					}
					
					Date date = new Date();
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
					
					
					String fileName = format.format(date)+movie.getId();
					
					if(contentType.equalsIgnoreCase("image/jpeg")) fileName += ".jpg";
					if(contentType.equalsIgnoreCase("image/png")) fileName += ".png";
					
					String persistPath = "E:\\MyCode\\Java\\MovieSource\\images\\download\\"+fileName;
					String picLink = "/images/download/"+fileName;
					
					File file = new File(persistPath);
					fileOut = new FileOutputStream(file);
					fileOut.write(fileByte);
					fileOut.close();
					
					map.clear();
					
					map.put("id", movie.getId());
					map.put("pic", picLink);
					boolean result = this.movieService.modifyPic(map);
					
					if(result){
						System.out.println(++num);
					}else{
						System.err.println(movie.getId());
					}
					
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(fileOut != null){
					fileOut.close();
				}
			}
			
		}
		return "";
		
	}
	
}
