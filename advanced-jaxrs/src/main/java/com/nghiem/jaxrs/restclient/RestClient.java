package com.nghiem.jaxrs.restclient;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.Response;

public class RestClient {

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();
        try {
            Response response = client.target("http://localhost:8080/advanced-jaxrs/api/status").request().get();
            String res = response.readEntity(String.class);
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            client.close();
        }
    }
}
