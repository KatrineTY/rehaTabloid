package com.javaschool;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.logging.Logger;

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

    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String subject = "queue/rehaQueue";

    @PostConstruct
    public void receive() throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        connection = connectionFactory.createConnection();
        connection.start();

        // Creating session for seding messages
        session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        // Getting the queue 'JCG_QUEUE'
        Destination destination = session.createQueue(subject);

        // MessageConsumer is used for receiving (consuming) messages
        consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MyMessageListener());
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