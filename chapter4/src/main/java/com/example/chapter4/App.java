package com.example.chapter4;


import com.example.chapter4.application.ports.input.RouterNetworkInputPort;
import com.example.chapter4.application.ports.output.RouterNetworkOutputPort;
import com.example.chapter4.application.usecases.RouterNetworkUseCase;
import com.example.chapter4.framework.adapters.input.RouterNetworkAdapter;
import com.example.chapter4.framework.adapters.input.rest.RouterNetworkRestAdapter;
import com.example.chapter4.framework.adapters.input.stdin.RouterNetworkCLIAdapter;
import com.example.chapter4.framework.adapters.output.file.RouterNetworkFileAdapter;
import com.example.chapter4.framework.adapters.output.h2.RouterNetworkH2Adapter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class App {

    RouterNetworkAdapter inputAdapter;
    RouterNetworkUseCase usecase;
    RouterNetworkOutputPort outputPort;

    public static void main(String... args) {
        var adapter = "cli";
        if (args.length > 0) {
            adapter = args[0];
        }
        new App().setAdapter(adapter);
    }

    void setAdapter(String adapter) {
        switch (adapter) {
            case "rest":
                outputPort = RouterNetworkH2Adapter.getInstance();
                usecase = new RouterNetworkInputPort(outputPort);
                inputAdapter = new RouterNetworkRestAdapter(usecase);
                rest();
                break;
            default:
                outputPort = RouterNetworkFileAdapter.getInstance();
                usecase = new RouterNetworkInputPort(outputPort);
                inputAdapter = new RouterNetworkCLIAdapter(usecase);
                cli();
        }
    }

    private void cli() {
        Scanner scanner = new Scanner(System.in);
        inputAdapter.processRequest(scanner);
    }

    private void rest() {
        try {
            System.out.println("REST endpoint listening on port 8080...");
            var httpserver = HttpServer.create(new InetSocketAddress(8080), 0);
            inputAdapter.processRequest(httpserver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}