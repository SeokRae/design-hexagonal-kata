package com.example.chapter3.domain.vo;

import java.util.Objects;

public class EventId {

    private final String id;

    private EventId(String id) {
        this.id = id;
    }

    public static EventId of(String id) {
        return new EventId(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EventId eventId)) return false;
        return Objects.equals(id, eventId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EventId{" +
                "id='" + id + '\'' +
                '}';
    }
}
