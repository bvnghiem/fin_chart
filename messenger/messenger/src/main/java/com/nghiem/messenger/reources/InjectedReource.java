package com.nghiem.messenger.reources;

import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.MatrixParam;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.UriInfo;

@Path("injections")
public class InjectedReource {

    @GET
    @Path("annotations")
    @Produces(MediaType.TEXT_PLAIN)
    public String getParamsUsingAnnotations(@MatrixParam("test") String param,
                                                @HeaderParam("customHeader") String headerValue,
                                                @CookieParam("cookie_test") String cookieValue) {
        //Matrix Param sample:  http://localhost:8080/messenger/api/injections;test=Hello
        return "Matrix Param: " + param //Hello
                + ", Header: " + headerValue
                + ", Cookie: " + cookieValue;
    }
    
    @GET
    @Path("context")
    @Produces(MediaType.TEXT_PLAIN)
    public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
        return "Path param: " + uriInfo.getPathParameters()
                + ", \nQuery Param: "+ uriInfo.getQueryParameters()
                + ", \nHeaders: " + headers.getRequestHeaders()
                + ", \nCookies: " + headers.getCookies();
    }
}
