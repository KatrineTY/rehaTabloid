package com.javaschool;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;
import java.util.logging.Logger;

@SessionScoped
@ManagedBean
@Slf4j
public class MyMessageListener implements MessageListener, Serializable {

    @Inject
    private EventsReceiverService eventsReceiverService;

    public MyMessageListener(EventsReceiverService eventsReceiverService) {
        this.eventsReceiverService = eventsReceiverService;
        log.info("Listener default created");
    }

    public MyMessageListener() {
        log.info("Listener default created");
    }

    @Override
    public void onMessage(Message message) {
        log.info("Message received");
        eventsReceiverService.requestEvents();
    }

}
