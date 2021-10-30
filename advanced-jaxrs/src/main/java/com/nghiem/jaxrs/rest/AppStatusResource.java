package com.nghiem.jaxrs.rest;

import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("status")
@Singleton
public class AppStatusResource {

    private int count = 0;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        count++;
        return "JAX-RS Application is running. Status check is called " + count + " time(s)";
    }
}
