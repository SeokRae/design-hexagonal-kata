package org.example.chapter1.adapters.input.output.file;

import org.example.chapter1.application.ports.output.RouterViewOutputPort;
import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterId;
import org.example.chapter1.domain.RouterType;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {

  private static volatile RouterViewFileAdapter instance;
  private final String filePath;

  private RouterViewFileAdapter(String filePath) {
    this.filePath = filePath;
  }

  public static RouterViewFileAdapter getInstance() {
    if (instance == null) {
      synchronized (RouterViewFileAdapter.class) {
        if (instance == null) {
          // 외부 주입?으로 수정할까?
          String filePath = "routers.txt";
          instance = new RouterViewFileAdapter(filePath);
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
    try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()))) {
      return lines
              .map(this::createRouterFrom)
              .toList();
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException("Failed to read routers from file", e);
    }
  }


  private Router createRouterFrom(String line) {
    String[] routerEntry = line.split(";");
    var id = routerEntry[0];
    var type = routerEntry[1];
    return new Router(RouterType.valueOf(type), RouterId.of(id));
  }

}
