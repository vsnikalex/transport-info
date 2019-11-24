package com.tsystems.transportinfo.sse;

import com.tsystems.transportinfo.model.DeliveryList;
import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.SseRequest;
import com.tsystems.transportinfo.model.TrucksStat;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.SseEventSink;
import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ApplicationScoped
public class MessageHandler implements Serializable {

    private final Map<String, SseRequest> requests = new ConcurrentHashMap<>();

    public void register(String uuid, SseRequest request) {
        log.info("register request:{}", uuid);
        requests.put(uuid, request);
    }

    public void deregister(String uuid) {
        log.info("deregister request:{}", uuid);
        SseRequest req = requests.remove(uuid);
        try (SseEventSink eventSink = req.getEventSink()) {
            eventSink.close();
        }
    }

    public void onMessage(@Observes DriversStat driverStat) {
        try {
            dispatchMessage(driverStat, "drivers stat");
        } catch (NullPointerException e) {
            log.info("DriversStat is empty");
        }
    }

    public void onMessage(@Observes TrucksStat trucksStat) {
        try {
            dispatchMessage(trucksStat, "trucks stat");
        } catch (NullPointerException e) {
            log.info("TrucksStat is empty");
        }
    }

    public void onMessage(@Observes DeliveryList deliveryList) {
        try {
            dispatchMessage(deliveryList, "delivery list");
        } catch (NullPointerException e) {
            log.info("DeliveryList is empty");
        }
    }

    private <T> void dispatchMessage(T obj, String name) {
        requests.forEach((uuid, request) -> {
            try {
                request.getEventSink().send(
                        request.getSse().newEventBuilder()
                                .mediaType(MediaType.APPLICATION_JSON_TYPE)
                                .id(UUID.randomUUID().toString())
                                .name(name)
                                .data(obj)
                                .build()
                );
            } catch (IllegalStateException e) {
                log.info("Remove unclosed SseEventSink from map");
                requests.remove(uuid);
            }
        });
    }

}
