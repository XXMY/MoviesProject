package com.cfw.movies.comment.service.remote.impl;

import com.cfw.movies.comment.service.CommentService;
import com.cfw.movies.comment.service.remote.RemoteCommentService;
import com.cfw.movies.commons.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Cfw on 2017/5/5.
 */
@Service("remoteCommentService")
public class RemoteCommentServiceImpl implements RemoteCommentService {

    @Autowired
    private CommentService commentService;

    /**
     * Creates and exports a new UnicastRemoteObject object using an
     * anonymous port.
     * <p>
     * <p>The object is exported with a server socket
     * created using the {@link RMISocketFactory} class.
     *
     * @throws RemoteException if failed to export object
     * @since JDK1.1
     */
    protected RemoteCommentServiceImpl() throws RemoteException {
    }

    @Override
    public boolean addComment(Integer movieId, String username, String comment, float score) throws RemoteException {
        return false;
    }

    @Override
    public List<Comments> getCommentsOfMovie(Integer movieId) throws RemoteException {
        return null;
    }
}
