package com.nghiem.jaxrs.rest;

import java.util.Date;

import org.glassfish.jersey.process.internal.RequestScoped;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("{pathParam}/test")
@RequestScoped
public class MyResource {

    @PathParam("pathParam") private String pathParamEx;
    @QueryParam("queryParam") private String queryParamEx;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String testMethod() {
        return "pathParam: " + pathParamEx + ", queryParam: " + queryParamEx;
    }

    @GET
    @Path("shortdate")
    @Produces("text/shortdate")
    public Date testDateMethod() {
        return new Date();
    }
}
