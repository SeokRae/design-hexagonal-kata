package org.example.chapter1.framework.adapters.output.file;

import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterType;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RouterViewFileAdapterMockTest {

  private static final String filePath = "src/test/resources/test_routers.txt";
  private static RouterViewFileAdapter adapter;

  @BeforeEach
  public void setUp() {
    // 인스턴스 생성
    adapter = RouterViewFileAdapter.getInstance(filePath);
  }

  @Order(1)
  @Test
  void testFetchRouters() {
    List<Router> routers = adapter.fetchRouters();

    // 결과 검증
    assertThat(routers).isNotNull()
            .hasSize(3)
            .extracting(Router::getRouterType)
            .containsExactlyInAnyOrder(RouterType.CORE, RouterType.EDGE, RouterType.EDGE);
  }

  @Order(2)
  @Test
  void testCreateRouterFromValidData() {
    String validLine = "ca23800e-9b5a-11eb-a8b3-0242ac130003;EDGE";
    Router router = adapter.createRouterFrom(validLine);

    assertThat(router).isNotNull();
    assertThat(router.getRouterId().toString()).contains("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    assertThat(router.getRouterType()).isEqualTo(RouterType.EDGE);
  }

  @Order(3)
  @Test
  void testCreateRouterFromInvalidData() {
    String invalidLine = "invalidData";
    assertThatThrownBy(() -> adapter.createRouterFrom(invalidLine))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Router data must contain ID and type, separated by a ';' character");
  }

}
