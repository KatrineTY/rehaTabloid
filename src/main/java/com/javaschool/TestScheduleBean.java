package com.javaschool;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Singleton
public class TestScheduleBean {

    @Inject
    private EventsReceiverService eventsReceiverService;

    @Schedule(hour = "*", minute = "*/60")
    void scheduleMe() {
        System.out.println("Scheduled request");
        eventsReceiverService.requestEvents();
    }

}