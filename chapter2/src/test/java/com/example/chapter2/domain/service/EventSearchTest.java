package com.example.chapter2.domain.service;

import com.example.chapter2.domain.entity.Event;
import com.example.chapter2.domain.vo.ParsePolicyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EventSearchTest {

    private EventSearch eventSearch;

    @BeforeEach
    void setUp() {
        eventSearch = new EventSearch();
    }

    @Test
    void givenUnparsedEvents_whenRetrieveEvents_thenShouldReturnParsedEvents() {
        List<String> unparsedEvents = Arrays.asList("event1", "event2", "event3");

        List<Event> parsedEvents = eventSearch.retrieveEvents(unparsedEvents, ParsePolicyType.REGEX);

        assertThat(parsedEvents).hasSize(3);
    }

    @Test
    void givenNoUnparsedEvents_whenRetrieveEvents_thenShouldReturnEmptyList() {
        List<String> unparsedEvents = Collections.emptyList();

        List<Event> parsedEvents = eventSearch.retrieveEvents(unparsedEvents, ParsePolicyType.REGEX);

        assertThat(parsedEvents).isEmpty();
    }
}