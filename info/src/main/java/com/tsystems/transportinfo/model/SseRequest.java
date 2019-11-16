package com.tsystems.transportinfo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;
import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class SseRequest implements Serializable {

    private Sse sse;
    private SseEventSink eventSink;

}
