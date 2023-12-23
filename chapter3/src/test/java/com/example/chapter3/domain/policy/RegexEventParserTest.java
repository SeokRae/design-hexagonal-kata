package com.example.chapter3.domain.policy;

import com.example.chapter3.domain.entity.Event;
import com.example.chapter3.domain.vo.Activity;
import com.example.chapter3.domain.vo.EventId;
import com.example.chapter3.domain.vo.Protocol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("이벤트 파서 테스트")
class RegexEventParserTest {

    private RegexEventParser parser;

    @BeforeEach
    void setUp() {
        parser = new RegexEventParser();
    }

    @DisplayName("성공적인 이벤트 파싱 테스트")
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

    @DisplayName("이벤트 포맷이 잘못된 경우 예외를 던진다")
    @Test
    void shouldThrowExceptionWhenEventFormatIsInvalid() {
        String event = "invalid event format";

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> parser.parseEvent(event))
                .withMessage("Invalid event format");
    }
}