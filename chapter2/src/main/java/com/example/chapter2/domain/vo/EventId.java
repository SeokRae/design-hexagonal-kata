package com.example.chapter2.domain.vo;

/**
 * 고유하게 식별 가능한 값 객체(EventId) <br/>
 */
public class EventId {

    private final String id;

    private EventId(String id){
        this.id = id;
    }

    // Factory Method - use this instead of constructor
    public static EventId of(String id){
        return new EventId(id);
    }

    @Override
    public String toString() {
        return "EventId{" +
                "id='" + id + '\'' +
                '}';
    }
}
