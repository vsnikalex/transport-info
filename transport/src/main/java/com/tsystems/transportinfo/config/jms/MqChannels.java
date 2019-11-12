package com.tsystems.transportinfo.config.jms;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

public class MqChannels {

    private static final String EXCHANGE = "myExchange";
    private static final String QUEUE = "myQueue";
    private static final String ROUTING_KEY = QUEUE;


    public static ConnectionFactory getFactory()  {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ti-rabbit");
        factory.setPort(5672);
        factory.setVirtualHost("transportinfo");
        factory.setUsername("admin");
        factory.setPassword("admin");

        return factory;
    }

    public static Connection getConnection() {
        ConnectionFactory factory = getFactory();
        Connection connection = null;
        try {
            connection = factory.newConnection();
        }
        catch (IOException | TimeoutException e) {
            System.out.println(e.getMessage());
        }

        return connection;
    }

    public static Channel getChannel(Connection connection) throws IOException {
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE, BuiltinExchangeType.FANOUT, true);
        channel.queueDeclare(QUEUE, true, false, false, null);
        channel.queueBind(QUEUE, EXCHANGE, ROUTING_KEY);

        return channel;
    }
}
