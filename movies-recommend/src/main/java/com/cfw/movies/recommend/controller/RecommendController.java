package com.cfw.movies.recommend.controller;

import com.cfw.movies.commons.controller.BaseController;
import com.cfw.movies.commons.enums.ResponseTypeEnum;
import com.cfw.movies.commons.model.Movie;
import com.cfw.movies.commons.model.User;
import com.cfw.movies.commons.vo.MoviesResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Duskrain on 2017/5/6.
 */
@Controller
@RequestMapping("Recommend")
public class RecommendController extends BaseController{

    /**
     * @author Fangwei_Cai
     * @time since 2016年5月31日 下午8:03:37
     * @return
     */
    @RequestMapping("/recommended")
    @ResponseBody
    public MoviesResponse recommendedMovies(HttpSession session){
        MoviesResponse response = new MoviesResponse();
        List<Movie> recommendMovies = null;

        try{
            Map<String,Object> map = (Map<String,Object>)session.getAttribute(session.getId());
            if(map != null){
                int userId = (int) map.get("id");
                User user = new User();
                user.setId(userId);
                recommendMovies = null; //this.movieService.getRecommendMovies(user);
            }else{
                recommendMovies = null; //this.movieService.getRecommendMovies(null);
            }

            response = buildResponse(ResponseTypeEnum.SUCCESS);
            response.setData(recommendMovies);

            return response;
        }catch(Exception e){
            response = buildResponse(ResponseTypeEnum.SYSTEM_ERROR);
            return response;
        }
    }
}
