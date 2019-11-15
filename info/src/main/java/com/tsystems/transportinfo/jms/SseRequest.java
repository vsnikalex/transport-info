package com.tsystems.transportinfo.jms;

import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.io.Serializable;

public class SseRequest implements Serializable {

    private Sse sse;
    private SseEventSink eventSink;

    public SseRequest(Sse sse, SseEventSink eventSink) {
        this.sse = sse;
        this.eventSink = eventSink;
    }

    public Sse getSse() {
        return sse;
    }

    public void setSse(Sse sse) {
        this.sse = sse;
    }

    public SseEventSink getEventSink() {
        return eventSink;
    }

    public void setEventSink(SseEventSink eventSink) {
        this.eventSink = eventSink;
    }

}
