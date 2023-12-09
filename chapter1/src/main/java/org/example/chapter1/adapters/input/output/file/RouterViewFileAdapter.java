package org.example.chapter1.adapters.input.output.file;

import org.example.chapter1.application.ports.output.RouterViewOutputPort;
import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterId;
import org.example.chapter1.domain.RouterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {
  private static final Logger log = LoggerFactory.getLogger(RouterViewFileAdapter.class);
  private static volatile RouterViewFileAdapter instance;
  private final String filePath;

  private RouterViewFileAdapter(String filePath) {
    this.filePath = filePath;
  }

  public static RouterViewFileAdapter getInstance(String filePath) {
    if (instance == null) {
      synchronized (RouterViewFileAdapter.class) {
        if (instance == null) {
          // 외부 주입?으로 수정할까?
          String notBlankFilePath = filePath.isBlank() ? "routers.txt" : filePath;
          instance = new RouterViewFileAdapter(notBlankFilePath);
        }
      }
    }
    return instance;
  }

  @Override
  public List<Router> fetchRouters() {
    return readFileAsString();
  }

  /**
   * Reads the contents of a file "routers.txt" and returns it as a list of Router objects.
   *
   * @return A list of Router objects read from the file.
   */

  private List<Router> readFileAsString() {
    Path path = Paths.get(filePath);
    log.info("Reading routers from file: {}", path.toAbsolutePath());
    if (!Files.exists(path)) {
      throw new IllegalArgumentException("File is not found");
    }

    try (Stream<String> lines = Files.lines(path)) {
      return lines
              .map(this::createRouterFrom)
              .toList();
    } catch (IOException e) {
      throw new RuntimeException("Failed to read routers from file", e);
    }
  }


  Router createRouterFrom(String line) {
    String[] routerEntry = line.split(";");
    if (routerEntry.length < 2) {
      throw new IllegalArgumentException("Router data must contain ID and type, separated by a ';' character");
    }
    var id = routerEntry[0];
    var type = routerEntry[1];
    return new Router(RouterType.valueOf(type), RouterId.of(id));
  }

}
