package com.javaschool;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

@Slf4j
@Singleton
public class TestScheduleBean {

    @Inject
    private EventsReceiverService eventsReceiverService;

    @Schedule(hour = "*", minute = "*/60")
    void scheduleMe() {
        log.info("Scheduled request");
        eventsReceiverService.requestEvents();
    }

}