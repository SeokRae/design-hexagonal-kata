package com.example.chapter2.domain.vo;

import java.util.UUID;

/**
 * The RouterId class represents the unique identifier of a Router.
 */
public class RouterId {

    private final UUID id;

    private RouterId(UUID id) {
        this.id = id;
    }

    /**
     * Generates a RouterId object with the specified ID. <br/>
     *
     * 1. 입력 문자열의 길이가 36인지 확인 <br/>
     * 2. 8번째, 13번째, 18번째, 23번째 문자가 하이픈('-')인지 확인 <br/>
     *
     * @param id The ID to be used for the RouterId object
     * @return a new instance of RouterId with the specified ID
     */
    public static RouterId withId(String id) {
        return new RouterId(UUID.fromString(id));
    }

    public static RouterId withoutId() {
        return new RouterId(UUID.randomUUID());
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
