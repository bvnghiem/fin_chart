package com.nghiem.messenger.service;

import static com.nghiem.messenger.database.Database.messages;

import java.util.ArrayList;
import java.util.List;

import com.nghiem.messenger.model.Comment;
import com.nghiem.messenger.model.Message;

import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.NotFoundException;

public class CommentService {


    public List<Comment> getAllComments(long msgId) {
        return new ArrayList<Comment>(messages.get(msgId).getComments().values());
    }

    public Comment addComment(long msgId, Comment comment) {
        messages.get(msgId).getComments().put(comment.getId(), comment);
        return comment;
    }

    public Comment updateComment(long msgId, Comment comment) {
        Comment oComment = getComment(msgId, comment.getId());
        if (comment.getComment() != null) {
            oComment.setComment(comment.getComment());
        }
        if (comment.getAuthor() != null) {
            oComment.setAuthor(comment.getAuthor());
        }
        if (comment.getCreated() != null) {
            oComment.setCreated(comment.getCreated());
        }
        return oComment;
    }

    public Comment getComment(long msgId, long commentId) {
        Message message = messages.get(msgId);
        if(message == null) {
            throw new NotFoundException("No message found with ID: " + msgId);
        }
        
        Comment comment = message.getComments().get(commentId);
        if(comment == null) {
            throw new NotFoundException("No comment found with ID: " + commentId);
        }
        
        return comment;
    }

    public Comment deleteComment(long msgId, long commentId) {
        return messages.get(msgId).getComments().remove(commentId);
    }
}
