package com.cfw.movies.home.controller;

import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.dto.MovieDetails;
import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.model.Comment;
import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.Type;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.commons.vo.MoviesResponse;
import com.cfw.movies.home.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * The controller contains movies' operations.
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午3:04:14
 */
@Controller
@RequestMapping("/Home")
public class HomeController extends BaseController {

	@Autowired
	private MovieService movieService;

	@Resource(name = "commentService")
	private CommentService commentService;
	
	/**
	 * Get the movies as list.
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午4:39:10
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/movies",method=RequestMethod.GET)
	@ResponseBody
	public MoviesResponse movieGet(Page page, @RequestParam(defaultValue="1")int flag){
		MoviesResponse response = null;
		
		List<Movie> movies = null;
		
		try{
			movies = movieService.getMovies(page,flag);

			if(movies.size()>0){
				response = buildResponse(ResponseTypeEnum.SUCCESS);
				response.setData(movies);
				return response;
			}else{
				response = buildResponse(ResponseTypeEnum.FAILED);
			}
		}catch(Exception e){
			response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);
		}

		return response;
	}
	
	@RequestMapping(value="/movies_count",method=RequestMethod.GET)
	@ResponseBody
	public MoviesResponse movieCount(){
		MoviesResponse response = new MoviesResponse();
		try{
			int count = movieService.countMovies();
			response = buildResponse(ResponseTypeEnum.SUCCESS);
			response.setData(count);

			return response;
		}catch(Exception e){
			response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);
		}
		
		return response;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:37:39
	 * @param id
	 * @return
	 */
	@RequestMapping("/one")
	@ResponseBody
	public MoviesResponse getOneMovie(Integer id){
		MoviesResponse response = new MoviesResponse();
		
		if(id <= 0){
			response = buildResponse(ResponseTypeEnum.PARAM_WRONG);
			return response;
		}
		
		try{
			Movie movie = this.movieService.getOneMovie(id);
			if(movie == null){
				response = buildResponse(ResponseTypeEnum.FAILED);
				return response;
			}

			List<Comment> comments = this.commentService.getCommentsOfMovie(id);

			MovieDetails movieDetails = new MovieDetails(movie, comments);

			response = buildResponse(ResponseTypeEnum.SUCCESS);
			response.setData(movieDetails);

			return response;
		}catch(Exception e){
			response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);
			return response;
		}
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午8:02:19
	 * @return
	 */
	@RequestMapping(value="/movieTypes",method=RequestMethod.GET)
	@ResponseBody
	public MoviesResponse movieTypes(){

		MoviesResponse response = new MoviesResponse();

		try{
			List<Type> movieTypes = this.movieService.getAllTypes();
			response = buildResponse(ResponseTypeEnum.SUCCESS);
			response.setData(movieTypes);

			return response;
		}catch(Exception e){
			response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);

			return response;
		}

	}
	
}
