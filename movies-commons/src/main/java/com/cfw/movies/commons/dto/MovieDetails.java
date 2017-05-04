package com.cfw.movies.commons.dto;

import com.cfw.movies.commons.model.Comments;
import com.cfw.movies.commons.model.Movies;

import java.util.List;

/**
 * Details of one movie, including movie information
 * and comments.
 * @author Fangwei_Cai
 * @time since 2016年5月7日 上午10:30:09
 */
public class MovieDetails {
	
	private Movies movie;
	
	private List<Comments> comments;

	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieDetails(Movies movie, List<Comments> comments) {
		super();
		this.movie = movie;
		this.comments = comments;
	}

	public Movies getMovie() {
		return movie;
	}

	public void setMovie(Movies movie) {
		this.movie = movie;
	}

	public List<Comments> getComments() {
		return comments;
	}

	public void setComments(List<Comments> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "MovieDetails [movie=" + movie + ", comments=" + comments + "]";
	}
	
	
}
