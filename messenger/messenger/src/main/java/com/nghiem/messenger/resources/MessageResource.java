package com.nghiem.messenger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import com.nghiem.messenger.bean.MessageFilterBean;
import com.nghiem.messenger.model.Message;
import com.nghiem.messenger.service.MessageService;

import jakarta.servlet.annotation.WebFilter;
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
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

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
    public Message getMessage(@PathParam("messageId") long id, @Context UriInfo uriInfo) throws Exception {
        Message message = messageService.getMessage(id);
        
        message.addLink(getUrl(id, uriInfo), "self");
        message.addLink(getProfileUrl(message, uriInfo), "profile");
        message.addLink(getCommentsUrl(message, uriInfo), "comments");
        
        return message;
    }

    @POST
    public Response addMessage(Message msg, @Context UriInfo uriInfo) throws URISyntaxException {
        msg.setId(messageService.getAllMessages().size() + 1);
        msg.setCreated(new Date());
        messageService.addMessages(msg);
        //return msg;
        
//        Custom repsonse
//        ResponseBuilder responseBuilder = Response.status(Status.CREATED)
//                .header("location", new URI("/messenger/api/messages/" + msg.getId()))
//                .entity(msg);
        
        //Another way using context param  UriInfo
        String msgIdStr = String.valueOf(msg.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(msgIdStr).build();
        return Response.created(uri)
                .entity(msg)
                .build();
    }
    
    @PUT
    @Path("/{messageId}")
    public Message updateMessage(@PathParam("messageId") long msgId, Message msg) {
        msg.setId(msgId);
        Message updatedMessage = messageService.updateMessage(msg);
        if(updatedMessage == null) {
            throw new NotFoundException("No message found with ID: " + msgId);
        } else {
            return updatedMessage;
        }
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
    public CommentResource getCommentResource() {
        return new CommentResource();
    }

    private String getUrl(long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(Long.toString(id))
                .build()
                .toString();
    }
    
    private String getProfileUrl(Message message, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder()
                .path(ProfileResource.class)
                .path(message.getAuthor())
                .build()
                .toString();
    }

    private String getCommentsUrl(Message message, UriInfo uriInfo) throws Exception {
        return uriInfo.getBaseUriBuilder()
                .path(MessageResource.class)
                .path(MessageResource.class, "getCommentResource")
                .path(CommentResource.class)
                .resolveTemplate("messageId", message.getId())
                .build()
                .toString();
    }
}
