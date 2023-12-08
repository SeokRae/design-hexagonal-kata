package org.example.chapter1.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;


class RouterTypeTest {
  @ParameterizedTest(name = "Given testRouterType {0}, when inputRouterType is {1}, then the result should be {2}")
  @CsvSource({
          "CORE, CORE, true",
          "CORE, EDGE, false",
          "EDGE, EDGE, true",
          "EDGE, CORE, false"
  })
  void testGetFilter(
          // 테스트할 RouterType
          final RouterType testRouterType,
          // 입력 RouterType
          final RouterType inputRouterType,
          // 기대값
          final boolean expected
  ) {
    // given
    Router router = new Router(inputRouterType, RouterId.of(inputRouterType.name() + "Id"));

    // when
    Predicate<Router> filter = testRouterType.getFilter();

    // then
    assertThat(filter.test(router)).isEqualTo(expected);
  }
}
