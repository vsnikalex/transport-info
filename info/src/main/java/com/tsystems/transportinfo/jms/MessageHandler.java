package com.tsystems.transportinfo.jms;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.SseEventSink;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

@ApplicationScoped
public class MessageHandler {

    @Inject
    Logger LOG;

    private final Map<String, SseRequest> requests = new ConcurrentHashMap<>();

    public void register(String uuid, SseRequest request) {
        LOG.log(Level.INFO, "register request:{0}", uuid);
        requests.put(uuid, request);
    }

    public void deregister(String uuid) {
        LOG.log(Level.INFO, "deregister request:{0}", uuid);
        SseRequest req = requests.remove(uuid);
        try (SseEventSink eventSink = req.getEventSink()) {
            eventSink.close();
        }
    }

    public void onMessage(@Observes Message msg) {
        requests.values().forEach(
                req -> req.getEventSink().send(
                        req.getSse().newEventBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_TYPE)
//                                .id(UUID.randomUUID().toString())
                                .name("message from cdi")
                                .data(msg)
                                .build()
                )
        );

    }

}
