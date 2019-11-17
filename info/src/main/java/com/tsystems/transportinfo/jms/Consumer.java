package com.tsystems.transportinfo.jms;

import com.tsystems.transportinfo.model.DriversStat;
import com.tsystems.transportinfo.model.TrucksStat;

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

	@Inject
	private Event<TrucksStat> trucksStatEvent;

	private final static Logger LOGGER = Logger.getLogger(Consumer.class.toString());

	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;

		if (rcvMessage instanceof TextMessage) {
			msg = (TextMessage) rcvMessage;
			LOGGER.info("Received Message from queue");

			DriversStat driversStat = new DriversStat(
					(int) (Math.random() * 100),
					(int) (Math.random() * 100),
					(int) (Math.random() * 100),
					(int) (Math.random() * 100)
			);
			driversStatEvent.fire(driversStat);

			TrucksStat trucksStat = new TrucksStat(
					(int) (Math.random() * 100),
					(int) (Math.random() * 100),
					(int) (Math.random() * 100),
					(int) (Math.random() * 100)
			);
			trucksStatEvent.fire(trucksStat);
		} else {
			LOGGER.warning("Message of wrong type: "
					+ rcvMessage.getClass().getName());
		}
	}

}
