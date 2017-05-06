package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.commons.reflect.SimpleAssign;
import com.cfw.movies.home.dao.MoviesDao;
import com.cfw.movies.home.mapper.MoviesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月8日 下午4:21:47
 */
@Repository("moviesDaoImpl")
public class MoviesDaoImpl implements MoviesDao {
	
	@Autowired
	private MoviesMapper moviesMapper;
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:25:32
	 */
	@Override
	public int insertMovie(Movie movies) {
		int result = this.moviesMapper.insertOne(movies);
		
		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午4:35:59
	 */
	@Override
	public List<Movie> selectMovies(Map<String, Object> map) {
		List<Movie> movies = this.moviesMapper.selectMovies(map);
		return movies;
	}

	@Override
	public List<Movie> selectMovies(Page page) {
		Map<String,Object> paramMap = new HashMap<String,Object>();

		boolean result = SimpleAssign.assignValueToMap(paramMap, page);
		if(result){
			return this.moviesMapper.selectMovies(paramMap);
		}
        return null;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午6:40:04
	 */
	@Override
	public List<Movie> selectFullMovies(Map<String, Object> map) {
		List<Movie> movies = this.moviesMapper.selectFullMovies(map);

		return movies;
	}

	@Override
	public List<Movie> selectFullMovies(Page page) {
		Map<String,Object> paramMap = new HashMap<String,Object>();

		boolean result = SimpleAssign.assignValueToMap(paramMap, page);
		if(result){
			return this.moviesMapper.selectMovies(paramMap);
		}

        return null;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午9:56:40
	 */
	@Override
	public Integer selectCount() {
		return this.moviesMapper.selectCount();
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:26:14
	 */
	@Override
	public Movie selectOne(int id) {
		Movie movie = this.moviesMapper.selectOne(id);

		return movie;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:03:48
	 */
	@Override
	public List<Movie> selectPic(Map<String, Object> map) {
		List<Movie> movies = this.moviesMapper.selectPic(map);

		return movies;
	}

	@Override
	public List<Movie> selectPic(int start, int length) {
		if( start < 0 || length < 0) return null;
		Map<String,Object> map = new HashMap<>();
		map.put("start",start);
		map.put("length",length);

		return this.selectPic(map);
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:03:53
	 */
	@Override
	public int updatePic(Map<String, Object> map) {
		int result = this.moviesMapper.updatePic(map);

		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午10:05:57
	 */
	@Override
	public int updateMovie(Movie movie) {
		int result = this.moviesMapper.updateOne(movie);

		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:44:12
	 */
	@Override
	public List<Movie> selectRecommendedMovies(User user) {
		List<Movie> recommendMovies = this.moviesMapper.selectRecommendedMovies(user);

		return recommendMovies;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午8:00:43
	 */
	@Override
	public List<Movie> selectTopScoreMoviesToRecommend() {
		List<Movie> recommendMovies = this.moviesMapper.selectTopScoreMoviesToRecommend();

		return recommendMovies;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午5:13:29
	 */
	@Override
	public Integer selectDesciptionId(int mid) {
		return this.moviesMapper.selectDesciptionId(mid);
	}
	
	

}
