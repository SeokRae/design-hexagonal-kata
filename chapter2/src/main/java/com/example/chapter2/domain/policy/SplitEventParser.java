package com.example.chapter2.domain.policy;

import com.example.chapter2.domain.entity.Event;
import com.example.chapter2.domain.vo.Activity;
import com.example.chapter2.domain.vo.EventId;
import com.example.chapter2.domain.vo.Protocol;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

/**
 * SplitEventParser 클래스는 분할 전략을 사용해 이벤트를 구문 분석하는 EventParser 인터페이스를 구현합니다
 * 이 클래스는 이벤트 문자열을 공백 구분자를 사용하여 필드로 분할하고 구문 분석된 필드에서 Event 객체를 생성합니다.
 */
public class SplitEventParser implements EventParser {

    // extract constants for readability
    private static final int EVENT_TIMESTAMP_INDEX = 0;
    private static final int EVENT_ID_INDEX = 1;
    private static final int EVENT_PROTOCOL_INDEX = 2;
    private static final int EVENT_SRC_HOST_INDEX = 3;
    private static final int EVENT_DST_HOST_INDEX = 5;

    /**
     * Parses an event string and creates an Event object.
     *
     * @param event the event string to be parsed
     * @return the parsed Event object
     */
    @Override
    public Event parseEvent(String event) {
        var fields = Arrays.asList(event.split(" "));

        if(fields.size() < 6) {
            throw new IllegalArgumentException("Event string not correctly formatted for parsing");
        }
        var timestamp = LocalDateTime.parse(fields.get(EVENT_TIMESTAMP_INDEX), formatter)
                .atOffset(ZoneOffset.UTC);
        var id = EventId.of(fields.get(EVENT_ID_INDEX));
        var protocol = Protocol.valueOf(fields.get(EVENT_PROTOCOL_INDEX));
        var activity = new Activity(fields.get(EVENT_SRC_HOST_INDEX), fields.get(EVENT_DST_HOST_INDEX));

        return new Event(timestamp, id, protocol, activity);
    }
}
