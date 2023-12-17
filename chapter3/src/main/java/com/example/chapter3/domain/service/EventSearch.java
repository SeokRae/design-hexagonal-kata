package com.example.chapter3.domain.service;

import com.example.chapter3.domain.entity.Event;
import com.example.chapter3.domain.vo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType) {
        var parsedEvents = new ArrayList<Event>();
        unparsedEvents.stream().forEach(event -> {
            parsedEvents.add(Event.parsedEvent(event, policyType));
        });
        return parsedEvents;
    }
}
