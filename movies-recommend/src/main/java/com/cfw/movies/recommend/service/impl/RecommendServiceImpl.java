package com.cfw.movies.recommend.service.impl;

import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.Recommend;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.commons.vo.MoviesResponse;
import com.cfw.movies.recommend.dao.RecommendDao;
import com.cfw.movies.recommend.dao.TempRecommendDao;
import com.cfw.movies.recommend.service.RecommendService;
import com.cfw.movies.recommend.thread.RecommendStatus;
import com.cfw.movies.recommend.thread.RecommendThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Fangwei_Cai
 * @time since 2016年5月15日 下午9:11:55
 */

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	private TempRecommendDao tempRecommendDao;
	
	@Autowired
	private RecommendDao recommendDao;
	
	
	/**
	 * (non-Javadoc)
	 * @see cfw.movies.service.RecommendService#startRecommend()
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 下午9:12:01
	 */
	@Override
	public boolean startRecommend() {
		RecommendThread thread = new RecommendThread("recommendThread");
		thread.start();
		return true;
	}

	/**
	 * (non-Javadoc)
	 * @see cfw.movies.service.RecommendService#getRecommendStaus()
	 * @author Fangwei_Cai
	 * @time since 2016年5月15日 下午9:12:06
	 */
	@Override
	public MoviesResponse getRecommendStaus() {
		MoviesResponse ajaxResult = RecommendStatus.statusMessage;
		
		return ajaxResult;
	}

	/**
	 * (non-Javadoc)
	 * @see cfw.movies.service.RecommendService#processRecommendData()
	 * @author Fangwei_Cai
	 * @time since 2016年5月29日 下午1:19:20
	 */
	@Override
	public boolean processRecommendData() {
		
		List<User> recommendUsers = this.tempRecommendDao.selectRecommendUsers();
		if(recommendUsers == null || recommendUsers.size()<=0) return false;
		
		List<Recommend> recommends = null;
		for(User user : recommendUsers){
			recommends = this.tempRecommendDao.selectRecommends(user);
			for(Recommend recommend : recommends){
				if(recommend == null || recommend.getPref() < 1500) continue;
				
				int result = this.recommendDao.insertOne(recommend);
				if(result <= 0){
					return false;
				}
			}
		}
		
		return true;
	}

	/**
	 * (non-Javadoc)
	 * @see cfw.movies.service.MovieService#getRecommendMovies(cfw.movies.model.Users)
	 * @author Fangwei_Cai
	 * @time since 2016年5月31日 下午7:47:48
	 */
	@Override
	public List<Movie> getRecommendMovies(User user) {
		List<Movie> recommendMovies = null;
		// While user is not logined.
		if (user == null) {
			recommendMovies = this.recommendDao.selectTopScoreMoviesToRecommend();

			return recommendMovies;
		}

		int recommendCount = this.recommendDao.selectRecommendedMoviesCount(user.getId());
		if (recommendCount > 0)
			recommendMovies = this.recommendDao.selectRecommendedMovies(user.getId());
		else
			recommendMovies = this.recommendDao.selectTopScoreMoviesToRecommend();


		return recommendMovies;
	}

}
