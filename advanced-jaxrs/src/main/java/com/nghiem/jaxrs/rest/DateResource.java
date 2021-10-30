package com.nghiem.jaxrs.rest;

import com.nghiem.jaxrs.entity.MyDate;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("date/{dateString}")
public class DateResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String toDate(@PathParam("dateString") MyDate dateString) {
        return "Got " + dateString;
    }
}
