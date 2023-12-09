package org.example.chapter1.framework.adapters.input.output.file;

import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RouterViewFileAdapterMockTest {

  @TempDir
  private Path tempDir;

  private RouterViewFileAdapter adapter;

  @BeforeEach
  public void setUp() throws Exception {
    // 테스트용 파일 생성
    Path testFilePath = tempDir.resolve("test_routers.txt");
    Files.writeString(testFilePath, "router1;CORE\nrouter2;EDGE");

    // 인스턴스 생성
    adapter = RouterViewFileAdapter.getInstance(testFilePath.toString());
  }

  @Test
  void testFetchRouters() throws Exception {
    List<Router> routers = adapter.fetchRouters();

    // 결과 검증
    assertThat(routers).isNotNull()
            .hasSize(2)
            .extracting(Router::getRouterType)
            .containsExactlyInAnyOrder(RouterType.CORE, RouterType.EDGE);
  }

  @Test
  void testCreateRouterFromValidData() {
    String validLine = "router1;CORE";
    Router router = adapter.createRouterFrom(validLine);

    assertThat(router).isNotNull();
    assertThat(router.getRouterId().toString()).contains("router1");
    assertThat(router.getRouterType()).isEqualTo(RouterType.CORE);
  }

  @Test
  void testCreateRouterFromInvalidData() {
    String invalidLine = "invalidData";
    assertThatThrownBy(() -> adapter.createRouterFrom(invalidLine))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("Router data must contain ID and type, separated by a ';' character");
  }

}
