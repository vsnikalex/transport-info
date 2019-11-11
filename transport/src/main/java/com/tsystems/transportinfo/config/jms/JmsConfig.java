package com.tsystems.transportinfo.config.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public ActiveMQConnectionFactory connectionFactory(){
        return new ActiveMQConnectionFactory("vm://localhost");
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        return new JmsTemplate(connectionFactory());
    }

//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory());
//        factory.setConcurrency("1-1");
//        return factory;
//    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestinationName("example.queue");
        container.setMessageListener(new MyJmsListener());
        return container;
    }

}
