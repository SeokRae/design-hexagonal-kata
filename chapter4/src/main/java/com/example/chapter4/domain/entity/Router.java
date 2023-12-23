package com.example.chapter4.domain.entity;


import com.example.chapter4.domain.vo.IP;
import com.example.chapter4.domain.vo.Network;
import com.example.chapter4.domain.vo.RouterId;
import com.example.chapter4.domain.vo.RouterType;

import java.util.Collections;
import java.util.List;

public class Router {

    private RouterType type;
    private RouterId id;
    private Switch networkSwitch;

    public Router() {

    }

    public Router(RouterType type, RouterId id) {
        this.type = type;
        this.id = id;
    }

    public Router(RouterType type, RouterId id, Switch networkSwitch) {
        this.type = type;
        this.id = id;
        this.networkSwitch = networkSwitch;
    }

    public boolean isType(RouterType type) {
        return this.type == type;
    }

    public void addNetworkToSwitch(Network network) {
        this.networkSwitch = networkSwitch.addNetwork(network, this);
    }

    public Network createNetwork(IP address, String name, int cidr) {
        return new Network(address, name, cidr);
    }

    public List<Network> retrieveNetworks() {
        return networkSwitch != null ? networkSwitch.getNetworks() : Collections.emptyList();
    }

    public RouterType getType() {
        return type;
    }

    public RouterId getId() {
        return id;
    }

    public Switch getNetworkSwitch() {
        return networkSwitch;
    }

    @Override
    public String toString() {
        return "Router{" +
                "type=" + type +
                ", id=" + id +
                ", networkSwitch=" + networkSwitch +
                '}';
    }
}