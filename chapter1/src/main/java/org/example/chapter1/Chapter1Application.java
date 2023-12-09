package org.example.chapter1;

import org.example.chapter1.framework.adapters.input.stdin.RouterViewCLIAdapter;

public class Chapter1Application {

  public static void main(String[] args) {

    RouterViewCLIAdapter routerViewCLIAdapter = new RouterViewCLIAdapter();
    final String type = "CORE";

    routerViewCLIAdapter.obtainRelatedRouters(type)
            .forEach(System.out::println);
  }

}
