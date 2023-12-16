package com.example.chapter2.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DisplayName("RouterId")
class RouterIdTest {

    @DisplayName("UUID id toString equals RouterId toString")
    @Test
    void shouldCreateRouterIdWithGivenId() {
        // given
        String id = UUID.randomUUID().toString();
        // given
        RouterId routerId = RouterId.withId(id);
        // then
        assertThat(id).isEqualTo(routerId.toString());
    }

    @DisplayName("UUID id toString not blank")
    @Test
    void shouldCreateRouterIdWithoutGivenId() {
        // given
        RouterId routerId = RouterId.withoutId();
        // then
        assertThat(routerId.toString()).isNotBlank();
    }

    @DisplayName("UUID id toString not blank")
    @Test
    void shouldThrowExceptionWhenInvalidUUIDProvided() {
        assertThrowsExactly(IllegalArgumentException.class, () -> RouterId.withId("invalidUUID"));
    }
}