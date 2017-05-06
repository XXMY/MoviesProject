package com.cfw.movies.commons.dto;

import com.cfw.movies.commons.model.Comment;
import com.cfw.movies.commons.model.Movie;

import java.util.List;

/**
 * Details of one movie, including movie information
 * and comments.
 * @author Fangwei_Cai
 * @time since 2016年5月7日 上午10:30:09
 */
public class MovieDetails {
	
	private Movie movie;
	
	private List<Comment> comments;

	public MovieDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MovieDetails(Movie movie, List<Comment> comments) {
		super();
		this.movie = movie;
		this.comments = comments;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "MovieDetails [movie=" + movie + ", comments=" + comments + "]";
	}
	
	
}
