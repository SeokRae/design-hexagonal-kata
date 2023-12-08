package org.example.chapter1.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RouterTest {
  private static final Router routerCore = new Router(RouterType.CORE, RouterId.of("coreId"));
  private static final Router routerEdge = new Router(RouterType.EDGE, RouterId.of("edgeId"));
  private static final List<Router> routers = Arrays.asList(routerCore, routerEdge);

  static Stream<Arguments> routerTypeAndExpectedRouterProvider() {
    return Stream.of(
            Arguments.of(RouterType.CORE, routerCore),
            Arguments.of(RouterType.EDGE, routerEdge)
    );
  }

  @ParameterizedTest(name = "Given routerType {0}, when filterRouterByType, then the result should be {1}")
  @MethodSource("routerTypeAndExpectedRouterProvider")
  void testFilterRouterByType(final RouterType routerType, final Router expectedRouter) {

    // when
    // 타입 별로 필터링
    final Predicate<Router> filter = Router.filterRouterByType(routerType);
    // 필터링된 라우터 리스트
    final List<Router> filteredRouters = Router.retrieveRouter(
            // 전체 라우터 리스트
            routers,
            // 타입 별로 필터링된 라우터 리스트
            filter);

    // then
    assertThat(filteredRouters.size()).isOne();
    assertThat(filteredRouters.get(0)).isEqualTo(expectedRouter);
  }

  @MethodSource("routerTypeAndExpectedRouterProvider")
  @ParameterizedTest(name = "Given routerType {0}, when getRouterType, then the result should be {1}")
  void testGetRouterType(final RouterType routerType, final Router expectedRouter) {

    // when
    assertThat(expectedRouter.getRouterType()).isEqualTo(routerType);
  }
}
