package com.example.chapter2;

import dev.davivieira.framework.adapters.input.stdin.RouterViewCLIAdapter;

public class App {

    public static void main(String... args) {
        var cli = new RouterViewCLIAdapter();
        var type = "EDGE";
        System.out.println(cli.obtainRelatedRouters(type));

    }
}
