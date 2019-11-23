package com.tsystems.transportinfo.jms;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.*;

@Slf4j
@ApplicationScoped
public class JmsService {

    @Resource(lookup = "java:/ConnectionFactory")
    ConnectionFactory cf;

    @Resource(lookup = "java:/queue/ModuleInteractionQueue")
    private Queue queue;

    public void sendReadyMessage() {
        try {
            Connection connection = cf.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer publisher = session.createProducer(queue);
            connection.start();
            TextMessage message = session.createTextMessage("READY");
            publisher.send(message);
        } catch (Exception e) {
            log.error("Error while sending ready-to-listen notification");
        }
    }

}
