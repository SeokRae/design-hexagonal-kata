package org.example.chapter1.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("RouterId tests")
class RouterIdTest {

  @DisplayName("RouterId should be created with the given id")
  @ValueSource(strings = {"testId", "anotherId"})
  @ParameterizedTest(name = "Test case {index}: Expected RouterId={0}")
  void testRouterIdCreation(final String routerId) {

    final RouterId actual = RouterId.of(routerId);

    String expected = "RouterId{id='" + routerId + "'}";
    assertThat(actual.toString()).contains(expected);
  }
}
