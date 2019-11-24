package com.tsystems.transportinfo.controller;

import com.tsystems.transportinfo.jms.JmsService;
import com.tsystems.transportinfo.model.SseRequest;
import com.tsystems.transportinfo.sse.MessageHandler;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.util.UUID;

@Slf4j
@Path("/cdievents")
@RequestScoped
public class SseCdiResource {

    @Inject
    private MessageHandler handler;

    @Inject
    private JmsService jmsService;

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public void eventStreamCdi(@Context Sse sse, @Context SseEventSink eventSink) {
        log.info("Sending ready-to-listen notification...");
        jmsService.sendReadyMessage();

        log.info("Register new SSE listener...");
        handler.register(UUID.randomUUID().toString(), new SseRequest(sse, eventSink));
    }

    @DELETE
    @Path("{uuid}")
    public void deregister(@PathParam("uuid") String uuid) {
        log.info("Deregister SSE listener uuid={}", uuid);
        handler.deregister(uuid);
    }

}
