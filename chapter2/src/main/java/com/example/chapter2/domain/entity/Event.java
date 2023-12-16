package com.example.chapter2.domain.entity;


import com.example.chapter2.domain.policy.RegexEventParser;
import com.example.chapter2.domain.policy.SplitEventParser;
import com.example.chapter2.domain.vo.Activity;
import com.example.chapter2.domain.vo.EventId;
import com.example.chapter2.domain.vo.ParsePolicyType;
import com.example.chapter2.domain.vo.Protocol;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * The Event class represents an event with a timestamp, event ID, protocol, and activity details.
 */
public class Event implements Comparable<Event> {

    // 이벤트가 발생한 시간
    private final OffsetDateTime timestamp;
    // 이벤트 ID
    private final EventId id;
    // 이벤트 프로토콜
    private final Protocol protocol;
    // 이벤트 활동
    private final Activity activity;

    public Event(OffsetDateTime timestamp, EventId id, Protocol protocol, Activity activity) {
        this.timestamp = timestamp;
        this.id = id;
        this.protocol = protocol;
        this.activity = activity;
    }

    /**
     * 지정된 구문 분석 정책에 따라 구문 분석되지 않은 이벤트를 구문 분석합니다.
     *
     * @param unparsedEvent the unparsed event to be parsed
     * @param policy        the parse policy to be used (REGEX or SPLIT)
     * @return the parsed Event
     * @throws IllegalArgumentException if an invalid parse policy is provided
     */
    public static Event parsedEvent(String unparsedEvent, ParsePolicyType policy) {
        return switch (policy) {
            case REGEX -> new RegexEventParser().parseEvent(unparsedEvent);
            case SPLIT -> new SplitEventParser().parseEvent(unparsedEvent);
            default -> throw new IllegalArgumentException("");
        };
    }

    @Override
    public int compareTo(Event event) {
        return timestamp.compareTo(event.timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Event event) {
            return (event.timestamp.equals(this.timestamp)
                    && event.id.equals(this.id)
                    && event.protocol.equals(this.protocol)
                    && event.activity.equals(this.activity)
            );
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, id, protocol, activity) + 31;
    }
}
