package com.tsystems.transportinfo.jms;

import com.tsystems.transportinfo.model.DriversStat;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

@MessageDriven(name = "TestQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "TestQueue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class Consumer implements MessageListener {

	@Inject
	private Event<DriversStat> driversStatEvent;

	private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());

	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Received Message from queue: " + msg.getText());

				DriversStat driversStat = new DriversStat(
						(int) (Math.random() * 100),
						(int) (Math.random() * 100),
						(int) (Math.random() * 100),
						(int) (Math.random() * 100)
				);

				driversStatEvent.fire(driversStat);
			} else {
				LOGGER.warning("Message of wrong type: "
						+ rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}

}
