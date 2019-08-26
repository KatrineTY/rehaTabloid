package com.javaschool;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Slf4j
public class EventsReceiverService {

    private List<EventDto> events = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public void requestEvents() {
        log.info("Request events");
        URL jsonUrl = null;
        try {
            jsonUrl = new URL("http://localhost:8081/RehabilitationClinic/events-for-tabloid");
            ObjectMapper mapper = new ObjectMapper();
            events = mapper.readValue(jsonUrl, events.getClass());
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<EventDto> getEvents() {
        return events;
    }

}
