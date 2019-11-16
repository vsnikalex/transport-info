package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.model.Message;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/messages")
public class Notifications {

    @Inject
    private Event<Message> messageEvent;

    @GET
    @Path("/new")
    public Response ping() {
        Message message = new Message("TEST SSE CONNECTION");
        messageEvent.fire(message);
        return Response.ok().entity("Message sent").build();
    }

}
