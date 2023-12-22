package com.example.chapter3.domain.policy;

import com.example.chapter3.domain.entity.Event;
import com.example.chapter3.domain.vo.Activity;
import com.example.chapter3.domain.vo.EventId;
import com.example.chapter3.domain.vo.Protocol;

import java.time.OffsetDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexEventParser implements EventParser {

    private static final String REGEX = "\\s*(?<timestamp>\\\"[^\\\"]+\\\")\\s+(?<id>\\S+)\\s+(?<protocol>\\S+)\\s+(?<activity>\\S+)\\s*";
    private static final Pattern pattern = Pattern.compile(REGEX);

    @Override
    public Event parseEvent(String event) {
        final Matcher matcher = pattern.matcher(event);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid event format");
        }

        var timestamp = OffsetDateTime.parse(matcher.group("timestamp").replace("\"", ""));
        var id = EventId.of(matcher.group("id"));
        var protocol = Protocol.valueOf(matcher.group("protocol"));
        var activity = new Activity(matcher.group("activity"), matcher.group("activity"));

        return new Event(timestamp, id, protocol, activity);
    }
}