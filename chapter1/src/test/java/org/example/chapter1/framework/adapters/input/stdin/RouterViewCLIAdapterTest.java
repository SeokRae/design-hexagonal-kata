package org.example.chapter1.framework.adapters.input.stdin;

import org.example.chapter1.application.usecases.RouterViewUseCase;
import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterId;
import org.example.chapter1.domain.RouterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RouterViewCLIAdapterTest {

  private RouterViewCLIAdapter adapter;
  private RouterViewUseCase routerViewUseCase;

  @BeforeEach
  public void setUp() {
    // RouterViewUseCase의 모의 객체 생성
    routerViewUseCase = mock(RouterViewUseCase.class);
    adapter = new RouterViewCLIAdapter();

    // setAdapters() 내부 로직을 대체하기 위해 필드에 직접 모의 객체 주입
    // 이는 RouterViewCLIAdapter 클래스의 구현에 따라 달라질 수 있음
    adapter.routerViewUseCase = routerViewUseCase;
  }

  @Test
  void testObtainRelatedRouters() {
    // 테스트용 데이터 설정
    String type = "CORE";
    List<Router> expectedRouters = Arrays.asList(
            new Router(RouterType.CORE, RouterId.of("router1")),
            new Router(RouterType.CORE, RouterId.of("router2"))
    );

    // 모의 객체 동작 정의
    when(routerViewUseCase.getRouters(any())).thenReturn(expectedRouters);

    // 테스트 실행
    List<Router> actualRouters = adapter.obtainRelatedRouters(type);

    // 결과 검증
    assertNotNull(actualRouters, "Returned router list should not be null");
    assertEquals(expectedRouters.size(), actualRouters.size(), "Returned router list size should match expected");
    // 추가적인 검증들, 예를 들어 리스트 내부의 요소들 검증 등
  }

}
