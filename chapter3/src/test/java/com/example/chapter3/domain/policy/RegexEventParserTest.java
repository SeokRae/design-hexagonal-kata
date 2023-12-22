package com.example.chapter3.domain.policy;

import com.example.chapter3.domain.entity.Event;
import com.example.chapter3.domain.vo.Activity;
import com.example.chapter3.domain.vo.EventId;
import com.example.chapter3.domain.vo.Protocol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class RegexEventParserTest {
    private RegexEventParser parser;

    @BeforeEach
    void setUp() {
        parser = new RegexEventParser();
    }

    @Test
    void shouldParseEventSuccessfully() {
        String event = "\"2022-01-01T00:00:00Z\" id1 IPV4 activity1";
        Event expectedEvent = new Event(
                OffsetDateTime.parse("2022-01-01T00:00:00Z"),
                EventId.of("id1"),
                Protocol.IPV4,
                new Activity("activity1", "activity1")
        );

        Event actualEvent = parser.parseEvent(event);

        assertThat(actualEvent).isEqualTo(expectedEvent);
    }

    @Test
    void shouldThrowExceptionWhenEventFormatIsInvalid() {
        String event = "invalid event format";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parseEvent(event))
                .withMessage("Invalid event format");
    }
}