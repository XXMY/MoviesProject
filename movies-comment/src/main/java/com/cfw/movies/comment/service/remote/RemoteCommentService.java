package com.cfw.movies.comment.service.remote;

import com.cfw.movies.commons.model.Comments;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Cfw on 2017/5/5.
 */
public interface RemoteCommentService extends Remote {

    boolean addComment(Integer movieId, String username, String comment, float score) throws RemoteException;

    List<Comments> getCommentsOfMovie(Integer movieId) throws RemoteException;

}
