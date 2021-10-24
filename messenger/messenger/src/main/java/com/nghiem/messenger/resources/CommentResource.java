package com.nghiem.messenger.resources;

import java.util.List;

import com.nghiem.messenger.model.Comment;
import com.nghiem.messenger.service.CommentService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {
    
    private CommentService commentService = new CommentService();
    
    @GET
    public List<Comment> getAllComments(@PathParam("messageId") long msgId) {
        return commentService.getAllComments(msgId);
    }
    
    @GET
    @Path("/{commentId}")
    public Comment getComment(@PathParam("messageId") long msgId, @PathParam("commentId") long commentId) {
        return commentService.getComment(msgId, commentId);
    }

    @POST
    public Comment addComment(@PathParam("messageId") long msgId, Comment comment) {
        comment.setId(getAllComments(msgId).size() + 1);
        return commentService.addComment(msgId, comment);
    }
    
    @PUT
    @Path("/{commentId}")
    public Comment updateComment(@PathParam("messageId") long msgId, @PathParam("commentId") long commentId, Comment comment) {
        comment.setId(commentId);
        return commentService.updateComment(msgId, comment);
    }

    @DELETE
    @Path("/{commentId}")
    public Comment deleteComment(@PathParam("messageId") long msgId, @PathParam("commentId") long commentId) {
        return commentService.deleteComment(msgId, commentId);
    }
}
