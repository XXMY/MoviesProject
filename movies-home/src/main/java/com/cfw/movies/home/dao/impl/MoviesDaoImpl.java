package com.cfw.movies.home.dao.impl;

import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.Movies;
import com.cfw.movies.commons.model.Users;
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
	public int insertMovie(Movies movies) {
		int result = this.moviesMapper.insertOne(movies);
		
		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午4:35:59
	 */
	@Override
	public List<Movies> selectMovies(Map<String, Object> map) {
		List<Movies> movies = this.moviesMapper.selectMovies(map);
		return movies;
	}

	@Override
	public List<Movies> selectMovies(Page page) {
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
	public List<Movies> selectFullMovies(Map<String, Object> map) {
		List<Movies> movies = this.moviesMapper.selectFullMovies(map);

		return movies;
	}

	@Override
	public List<Movies> selectFullMovies(Page page) {
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
	public Long selectCount() {
		Long count = this.moviesMapper.selectCount();
		return count;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:26:14
	 */
	@Override
	public Movies selectOne(int id) {
		Movies movie = this.moviesMapper.selectOne(id);

		return movie;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:03:48
	 */
	@Override
	public List<Movies> selectPic(Map<String, Object> map) {
		List<Movies> movies = this.moviesMapper.selectPic(map);

		return movies;
	}

	@Override
	public List<Movies> selectPic(Long start, int length) {
		if(start == null || start < 0 || length < 0) return null;
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
	public int updateMovie(Movies movie) {
		int result = this.moviesMapper.updateOne(movie);

		return result;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:44:12
	 */
	@Override
	public List<Movies> selectRecommendedMovies(Users user) {
		List<Movies> recommendMovies = this.moviesMapper.selectRecommendedMovies(user);

		return recommendMovies;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午8:00:43
	 */
	@Override
	public List<Movies> selectTopScoreMoviesToRecommend() {
		List<Movies> recommendMovies = this.moviesMapper.selectTopScoreMoviesToRecommend();

		return recommendMovies;
	}

	/**
	 * (non-Javadoc)
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午5:13:29
	 */
	@Override
	public Long selectDesciptionId(int mid) {
		Long result = this.moviesMapper.selectDesciptionId(mid);
		return result;
	}
	
	

}
