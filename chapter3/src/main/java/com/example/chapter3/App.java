package com.example.chapter3;


import com.example.chapter3.domain.vo.IP;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.RouterId;
import com.example.chapter3.framework.adapters.input.stdin.RouterNetworkCLIAdapter;

public class App {

    public static void main(String... args) {
        var cli = new RouterNetworkCLIAdapter();
        var routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
        var network = new Network(new IP("20.0.0.0"), "Marketing", 8);
        var router = cli.addNetwork(routerId, network);
        System.out.println(router);
    }
}