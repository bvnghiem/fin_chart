package com.nghiem.messenger.resources;

import java.util.Date;
import java.util.List;

import com.nghiem.messenger.bean.MessageFilterBean;
import com.nghiem.messenger.model.Message;
import com.nghiem.messenger.service.MessageService;

import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("messages")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MessageResource {

    private MessageService messageService = new MessageService();

    @GET
    public List<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
        if (filterBean.getYear() > 0) {
            return messageService.getAllMessagesForYear(filterBean.getYear());
        } else if (filterBean.getMaxSize() > 0) {
            return messageService.getAllMessagesPaginated(filterBean.getStartIndex(), filterBean.getMaxSize());
        } else {
            return messageService.getAllMessages();
        }
    }
    
    @GET
    @Path("/{messageId}")
    public Message getMessage(@PathParam("messageId") long id) {
        return messageService.getMessage(id);
    }
    
    @POST
    public Message addMessage(Message msg) {
        msg.setId(messageService.getAllMessages().size() + 1);
        msg.setCreated(new Date());
        messageService.addMessages(msg);
        return msg;
    }
    
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long msgId, Message msg) {
        msg.setId(msgId);
        return messageService.updateMessage(msg);
    }

    @DELETE
    @Path("/{messageId}")
    public Message deleteMessage(@PathParam("messageId") long msgId) {
        Message deleteMessage = messageService.deleteMessage(msgId);
        if (deleteMessage == null) {
            throw new NotFoundException("Message not found");
        }
        return deleteMessage;
    }
    
    @Path("/{messageId}/comments")
    public CommentResource getCommentsResource() {
        return new CommentResource();
    }
}
