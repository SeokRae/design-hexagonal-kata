package com.example.chapter2.domain.service;

import com.example.chapter2.domain.entity.Event;
import com.example.chapter2.domain.vo.ParsePolicyType;

import java.util.ArrayList;
import java.util.List;

public class EventSearch {

    public List<Event> retrieveEvents(List<String> unparsedEvents, ParsePolicyType policyType) {
        var parsedEvents = new ArrayList<Event>();
        // stream().forEach() -> forEach() 로 변경
        unparsedEvents.forEach(event ->
                parsedEvents.add(Event.parsedEvent(event, policyType)));
        return parsedEvents;
    }
}
