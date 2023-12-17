package com.example.chapter2.domain.entity;

import com.example.chapter2.domain.vo.Activity;
import com.example.chapter2.domain.vo.EventId;
import com.example.chapter2.domain.vo.ParsePolicyType;
import com.example.chapter2.domain.vo.Protocol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EventTest {
    @DisplayName("값 검증 테스트")
    @Test
    void testConstructorAndGetters() {
        OffsetDateTime timestamp = OffsetDateTime.now();
        EventId id = EventId.of("event1");
        Protocol protocol = Protocol.IPV4;
        Activity activity = new Activity("sourceHostValue", "destinationHostValue");
        Event expectedEvent = new Event(timestamp, id, protocol, activity);
        Event actualEvent = new Event(timestamp, id, protocol, activity);

        assertThat(actualEvent)
                .usingRecursiveComparison().isEqualTo(expectedEvent);
    }

    @DisplayName("지정된 구문 분석 정책에 따라 구문 분석되지 않은 이벤트를 구문 분석합니다.")
    @CsvSource(value = {"your_unparsed_event_string,REGEX", "your_unparsed_event_string,SPLIT"})
    @ParameterizedTest(name = "{index} {displayName} {arguments}")
    void testParsedEvent(String unparsedEvent, ParsePolicyType policy) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Event.parsedEvent(unparsedEvent, policy));
    }

    @DisplayName("구문 분석 시 예외 발생 테스트")
    @Test
    void testParsedEventWithInvalidPolicy() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Event.parsedEvent("your_unparsed_event_string", ParsePolicyType.valueOf("invalid")));
    }

    @Test
    void testCompareTo() {
        OffsetDateTime now = OffsetDateTime.now();
        Event event1 = new Event(now, EventId.of("event1"), Protocol.IPV4, new Activity("sourceHostValue", "destinationHostValue"));
        Event event2 = new Event(now.plusHours(1), EventId.of("event2"), Protocol.IPV4, new Activity("sourceHostValue", "destinationHostValue"));

        // event1이 event2보다 이전임을 확인
        assertThat(event1.compareTo(event2))
                .isLessThan(0);
    }

    @DisplayName("동일성 및 동등성 검증 테스트")
    @Test
    void testEqualsAndHashCode() {
        OffsetDateTime now = OffsetDateTime.now();
        EventId id = EventId.of("event1");
        Protocol protocol = Protocol.IPV4;
        Activity activity = new Activity("sourceHostValue", "destinationHostValue");

        Event event1 = new Event(now, id, protocol, activity);
        Event event2 = new Event(now, id, protocol, activity);

        // 두 이벤트는 같은 값을 가지므로 동등
        // 'equals' 메서드를 통해 event1과 event2의 내용이 같은지 확인
        assertThat(event1).isEqualTo(event2);
        // 일관성 검증: 동등한 객체는 동일한 해시코드를 가져야함을 확인
        // 'hashCode' 메서드를 통해 event1과 event2의 해시코드가 같은지 확인
        assertThat(event1.hashCode()).isEqualTo(event2.hashCode());

        Event event3 = new Event(now.plusHours(1), id, protocol, activity);

        // 두 이벤트는 다른 값을 가지므로 동등하지 않음
        // 동등성 검증: 'equals' 메서드를 통해 event1과 event3의 내용이 다른지 확인
        assertThat(event1).isNotEqualTo(event3);
        // 동등성과 일관성 검증: 동등하지 않은 객체는 다른 해시코드를 가져야함을 확인
        assertThat(event1.hashCode()).isNotEqualTo(event3.hashCode());
    }
}