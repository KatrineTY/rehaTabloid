package com.javaschool;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.jms.*;

@Startup
@Singleton
@Slf4j
public class Receiver {

    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    @Inject
    private EventsReceiverService eventsReceiverService;

    private static String url = "tcp://192.168.99.100:61616";
    private static String subject = "queue/rehaQueueTabloid";

    @PostConstruct
    public void receive() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();
        log.info("Connected to activemq");

        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(subject);

        consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MyMessageListener(eventsReceiverService));
        eventsReceiverService.requestEvents();
    }

    @PreDestroy
    public void cleanup() {
        try {
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            log.info(e.toString());
        }
    }

}