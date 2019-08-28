package com.javaschool;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@ApplicationScoped
@Slf4j
public class EventsReceiverService {
    @Inject
    private EventsView eventsView;

    @SuppressWarnings("unchecked")
    public void requestEvents() {
        log.info("Request events");
        URL jsonUrl = null;
        try {
            jsonUrl = new URL("http://localhost:9595/RehabilitationClinic/events-for-tabloid");
            ObjectMapper mapper = new ObjectMapper();
            List<Map> request = mapper.readValue(jsonUrl, eventsView.getEvents().getClass());
            eventsView.setEvents(fillEvents(request));
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private List<EventDto> fillEvents(List<Map> request){
        List<EventDto> events = new ArrayList<>();
        for(Map map: request){
            EventDto event = new EventDto();
            event.setBuilding((String) map.get("building"));
            event.setComment((String) map.get("comment"));
            event.setDate(((String)map.get("date")).replace("T"," "));
            event.setDose((String) map.get("dose"));
            event.setNurseName((String) map.get("nurseName"));
            event.setPatientName((String) map.get("patientName"));
            event.setPromedKind((String) map.get("promedKind"));
            event.setPromedName((String) map.get("promedName"));
            event.setStatus((String) map.get("status"));
            event.setWard((Integer) map.get("ward"));
            events.add(event);
        }
        return events;
    }

}
