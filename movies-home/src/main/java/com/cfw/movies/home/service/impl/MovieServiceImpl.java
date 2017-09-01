package com.cfw.movies.home.service.impl;

import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.*;
import com.cfw.movies.commons.reflect.SimpleAssign;
import com.cfw.movies.home.dao.*;
import com.cfw.movies.home.service.MovieService;
import com.cfw.plugins.rmi.annotation.CRmiExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月2日 下午5:55:41
 */
@Service("movieService")
@CRmiExport
public class MovieServiceImpl implements MovieService {

	@Autowired
	private TypesDao typesDaoImpl;
	
	@Autowired
	private MoviesDao moviesDaoImpl;
	
	@Autowired
	private AbstractsDao abstractsDaoImpl;

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:35:57
	 */
	@Override
	public List<Type> getAllTypes() {
		List<Type> movieTypes = typesDaoImpl.findAll();
		
		return movieTypes;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午11:44:49
	 */
	@Override
	public boolean addType(Type type) {
		int insertTypeResult = typesDaoImpl.insertType(type);
		return insertTypeResult>0 ? true : false;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:36:06
	 */
	@Override
	public boolean addMovie(Movie movies) {
		
		// Persist movie's abstract first.
		boolean addAbstractResult = addDescription(movies.getDescription());
		
		// Movie's abstract persist succeed then persist the movie.
		int insertMovieResult = 0;
		if(addAbstractResult){
			insertMovieResult = moviesDaoImpl.insertMovie(movies);
		}
		
		return insertMovieResult>0 ? true : false;
	}
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:45:40
	 */
	@Override
	public boolean addDescription(Description abstracts) {
		int insertAbstractResult = abstractsDaoImpl.insertDescription(abstracts);
		
		return insertAbstractResult>0 ? true : false;
	}

	/**
	 *
	 * @param page<Page>
	 * @param flag <br>
	 * 	Use flag to identify the different usage
	 * of this method.<br>
	 * 	1: Get movie list for management page in table.<br>
	 * 	In this case, we just need id, name, type, score of
	 * movie.<br>
	 *  2: Get movie list for visitors, in index page and
	 *  search page and others.<br>
	 *  We need to get full information of movies.
     * @return
     */
	@Override
	public List<Movie> getMovies(Page page, int flag) {
		List<Movie> movies = null;

		Map<String,Object> paramMap = new HashMap<String,Object>();

		boolean result = SimpleAssign.assignValueToMap(paramMap, page);
        switch(flag){
            case 1:
                movies = moviesDaoImpl.selectMovies(paramMap);
                break;
            case 2:
                movies = moviesDaoImpl.selectFullMovies(paramMap);
                break;
        }
		
		if(movies == null || movies.size() == 0) return movies;
		
		List<Type> types = this.getAllTypes();
		for(Movie movie : movies){
			String movieTypeStr = movie.getType();
			String [] typeStrArr = movieTypeStr.split("_");
			String typeName = "";
			for(String typeStr : typeStrArr){
				for(Type type : types){
					if(type.getId() == Integer.parseInt(typeStr)){
						typeName += type.getType_name() + "/";
					}
				}
			}

			if(!StringUtils.isEmpty(typeName))
				movie.setType(typeName.substring(0, typeName.length()-1));

		}
		
		return movies;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午9:58:14
	 */
	@Override
	public int countMovies() {
		try{
			int count = moviesDaoImpl.selectCount();
			return count;
		}catch(Exception e){
			System.out.println("countMovie exception");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:28:07
	 */
	@Override
	public Movie getOneMovie(int id) {
		try{
			Movie movie = this.moviesDaoImpl.selectOne(id);

			return movie;
		}catch (Exception e){
			System.out.println("getOneMovie exception");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:06:18
	 */
	@Override
	public List<Movie> findPic(Map<String, Object> map) {
		int start = (Integer) map.get("start");
		int length = (Integer) map.get("length");
		try{
			List<Movie> movies = this.moviesDaoImpl.selectPic(start, length);

			return movies;
		}catch(Exception e){
			System.out.println("findPic exception");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:06:21
	 */
	@Override
	public boolean modifyPic(Map<String, Object> map) {
		int result = this.moviesDaoImpl.updatePic(map);

		if(result>0){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午10:08:14
	 */
	@Override
	public boolean deleteMovie(int... mids) {
		for(int mid : mids){
			Movie movie = new Movie();
			movie.setId(mid);
			movie.setIsdeleted(true);
			
			int deleteResult = this.moviesDaoImpl.updateMovie(movie);
			if(deleteResult <= 0) return false;
		}
		
		return true;
	}


	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午4:52:32
	 */
	@Override
	public boolean modifyMoive(Movie movie) {
		
		if(movie.getId()<=0) return false;
		
		int descriptionId = this.moviesDaoImpl.selectDesciptionId(movie.getId());
		if(descriptionId <=0 ) return false;
		
		Description description = movie.getDescription();
		description.setId(descriptionId);
		int updateDescriptionResult = this.abstractsDaoImpl.updateOne(description);
		
		if(updateDescriptionResult <= 0) return false;
		
		int updateMovieResult = this.moviesDaoImpl.updateMovie(movie);
		
		if(updateMovieResult > 0) return true;
		
		return false;
	}
}
