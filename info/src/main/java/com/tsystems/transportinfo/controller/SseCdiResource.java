package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.model.SseRequest;
import com.tsystems.transportinfo.sse.MessageHandler;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.UUID;

@Path("/cdievents")
@RequestScoped
public class SseCdiResource {

    @Inject
    MessageHandler handler;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventStreamCdi(@Context Sse sse, @Context SseEventSink eventSink) {
        handler.register(UUID.randomUUID().toString(), new SseRequest(sse, eventSink));
    }


    @DELETE
    @Path("{uuid}")
    public void deregister(@PathParam("uuid") String uuid) {
        handler.deregister(uuid);
    }

}
