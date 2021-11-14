package com.nghiem.messenger.restclient;

import com.nghiem.messenger.model.Message;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class RestClient {

    private static final String MESSAGES_API = "messages";
    private static final String MESSENGER_BASE = "http://localhost:8080/messenger/api/";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        try {
            WebTarget baseTarget = client.target(MESSENGER_BASE);
            WebTarget messagesTarget = baseTarget.path(MESSAGES_API);
            WebTarget singleMessageTarget = messagesTarget.path("{messageId}");
            
            Message message = singleMessageTarget.resolveTemplate("messageId", 1)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Message.class);
            System.out.println(message.getMessage());

            Message message2 = singleMessageTarget.resolveTemplate("messageId", 2)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Message.class);
            System.out.println(message2.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

}
