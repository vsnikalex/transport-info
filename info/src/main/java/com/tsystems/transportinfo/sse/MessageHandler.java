package com.tsystems.transportinfo.sse;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.SseRequest;
import com.tsystems.transportinfo.model.TrucksStat;

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

    public void onMessage(@Observes DriversStat driverStat) {
        dispatchMessage(driverStat, "drivers stat");
    }

    public void onMessage(@Observes TrucksStat trucksStat) {
        dispatchMessage(trucksStat, "trucks stat");
    }

    private <T> void dispatchMessage(T obj, String name) {
        requests.values().forEach(
                req -> req.getEventSink().send(
                        req.getSse().newEventBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                                .id(UUID.randomUUID().toString())
                                .name(name)
                                .data(obj)
                                .build()
                )
        );
    }

}
