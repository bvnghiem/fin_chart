package com.nghiem.messenger.restclient;

import java.util.ArrayList;
import java.util.List;

import com.nghiem.messenger.model.Message;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public class RestClient {

    private static final String MESSAGES_API = "messages";
    private static final String MESSENGER_BASE = "http://localhost:8080/messenger/api/";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget baseTarget = client.target(MESSENGER_BASE);
            WebTarget messagesTarget = baseTarget.path(MESSAGES_API);
            WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
            
            //POST
            Message msg = new Message(1, "Test create", "Nghiem");
            Response postResponse = messagesTarget.request().post(Entity.json(msg));
            System.out.println("Create message status: " + postResponse.getStatusInfo());
            
            //verify POST data is saved on server
            Message message3 = singleMessageTarget.resolveTemplate("messageId", 1)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Message.class);
            System.out.println(message3.getMessage());
            
            //Invocation
            Invocation getInv = prepareGetMessagesByYear(messagesTarget, 2021);
            List<Message> messages = (List<Message>) getInv.invoke(GenericType.forInstance(new ArrayList<Message>()));
            System.out.println(messages.size());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    private static Invocation prepareGetMessagesByYear(WebTarget messagesTarget, int year) {
        return messagesTarget
                .queryParam("year", year)
                .request(MediaType.APPLICATION_JSON)
                .buildGet();
    }

}
