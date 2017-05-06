package com.cfw.movies.home.service;


import com.cfw.movies.commons.dto.Page;
import com.cfw.movies.commons.model.*;

import java.util.List;
import java.util.Map;

/**
 * @author Fangwei_Cai
 * @time since 2016年4月2日 下午5:54:26
 */
public interface MovieService {
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月2日 下午5:54:52
	 */
	List<Type> getAllTypes();
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月11日 上午11:44:22
	 */
	boolean addType(Type type);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:35:24
	 */
	boolean addMovie(Movie movies);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年4月8日 下午4:44:47
	 */
	boolean addDescription(Description abstracts);
	
	/**
	 * <b>Get movies in list.</b><p>
	 * We may have many place need to get movies
	 * in list, and we need to identify them.
	 * For now, we use flag to differ. The value 
	 * and it's means as parameter says.
	 * 
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 上午11:17:49
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
	 * 
	 * 
	 * @return
	 */
	List<Movie> getMovies(Page page, int flag);

	/**
	 * Count the number of movies.
	 * @author Fangwei_Cai
	 * @time since 2016年4月24日 下午9:57:24
	 * @return
	 */
	int countMovies();

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月7日 上午12:27:18
	 * @param id
	 * @return
	 */
	Movie getOneMovie(int id);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:02:43
	 * @param map
	 * @return
	 */
	List<Movie> findPic(Map<String, Object> map);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午3:03:02
	 * @param map
	 * @return
	 */
	boolean modifyPic(Map<String, Object> map);

	/**
	 * @author Fangwei_Cai
	 * @time since 2016年5月8日 下午10:07:56
	 * @param mids
	 * @return
	 */
	boolean deleteMovie(int... mids);
	
	/**
	 * @author Fangwei_Cai
	 * @time since 2016年6月1日 下午4:52:17
	 * @param movie
	 * @return
	 */
	boolean modifyMoive(Movie movie);
}
