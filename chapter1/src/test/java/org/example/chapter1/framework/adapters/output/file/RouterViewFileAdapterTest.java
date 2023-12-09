package org.example.chapter1.framework.adapters.output.file;

import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterType;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("파일 접근 Adapter 테스트")
class RouterViewFileAdapterTest {

  private static final String filePath = "src/test/resources/test_routers.txt";
  private static RouterViewFileAdapter adapter;

  @BeforeEach
  public void setUp() {
    // 라우팅 정보를 가져오는 Adapter를 초기화
    adapter = RouterViewFileAdapter.getInstance(filePath);
  }

  @Order(1)
  @Test
  void testSingletonInstance() {
    // 싱글턴 패턴으로 구현된 RouterViewFileAdapter의 인스턴스가 실제로 싱글턴인지 확인
    RouterViewFileAdapter firstInstance = RouterViewFileAdapter.getInstance(filePath);
    RouterViewFileAdapter secondInstance = RouterViewFileAdapter.getInstance(filePath);
    assertThat(firstInstance).isSameAs(secondInstance);
  }

  @Order(2)
  @Test
  void testFetchRouters() {
    // fetchRouters() 를 통해 Router 정보를 가져오는지 확인
    List<Router> routers = adapter.fetchRouters();
    assertThat(routers).isNotNull();
  }

  @Order(3)
  @Test
  void testReadFileAsStringSuccess() {
    // readFileAsString의 성공적 실행을 테스트
    // 이를 위해 파일 읽기를 모의할 수 있음
  }

  @Order(4)
  @Test
  void testReadFileAsStringFailure() {
    // readFileAsString의 실패 케이스 테스트 (예: 파일을 찾지 못하는 경우)
    // Files.lines() 메서드가 예외를 던지도록 Mockito를 사용하여 모의할 수 있음
  }

  @Order(5)
  @Test
  void testCreateRouterFrom() {
    // given
    String line = "router1;CORE";
    // when
    Router router = adapter.createRouterFrom(line);
    // then
    assertThat(router).isNotNull();
    assertThat(router.getRouterId().toString()).contains("router1");
    assertThat(router.getRouterType()).isEqualTo(RouterType.CORE);
  }

  @Order(6)
  @Test
  void testCreateRouterFromInvalidData() {
    // 잘못된 형식의 라인으로 라우터를 생성할 때의 오류 처리를 테스트
    String invalidLine = "invalidData";
    assertThatThrownBy(() -> adapter.createRouterFrom(invalidLine))
            .isInstanceOf(RuntimeException.class)
            .hasMessageContaining("Router data must contain ID and type, separated by a ';' character");
  }
}
