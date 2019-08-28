package com.javaschool;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.*;
import java.util.stream.Collectors;

@Named
@Getter
@Setter
@ApplicationScoped
public class EventsView {

    private List<EventDto> events = new ArrayList<>();
    private List<EventDto> filteredEvents = new ArrayList<>();

    public List<String> getStatuses(){
        return  events.stream().map(EventDto::getStatus).distinct().collect(Collectors.toList());
    }

    public List<String> getBuildings(){
        return  events.stream().map(EventDto::getBuilding).distinct().collect(Collectors.toList());
    }

    public List<EventDto> getEvents() {
        return events;
    }

    public List<EventDto> getFilteredEvents() {
        return filteredEvents;
    }

}
