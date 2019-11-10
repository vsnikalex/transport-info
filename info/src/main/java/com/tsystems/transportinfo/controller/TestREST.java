package com.tsystems.transportinfo.controller;

import javax.faces.bean.RequestScoped;
import javax.ws.rs.*;

@Path("/test")
@RequestScoped
public class TestREST {

    @GET
    @Path("/{id:[0-9][0-9]*}")
    public String test(@PathParam("id") long id) {
        return Long.toString(id);
    }

}
