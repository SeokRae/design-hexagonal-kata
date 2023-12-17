package com.example.chapter3.domain.entity;

import com.example.chapter3.domain.vo.IP;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.SwitchId;
import com.example.chapter3.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;

public class Switch {

    private final SwitchType switchType;
    private final SwitchId switchId;
    private final List<Network> networks;
    private final IP address;

    public Switch(SwitchType switchType, SwitchId switchId, List<Network> networks, IP address) {
        this.switchType = switchType;
        this.switchId = switchId;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Router router) {
        List<Network> newNetworks = new ArrayList<>();

        router.retrieveNetworks().forEach(net -> {
            newNetworks.add(net);
        });

        newNetworks.add(network);
        return new Switch(this.switchType, this.switchId, newNetworks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    @Override
    public String toString() {
        return "Switch{" +
                "switchType=" + switchType +
                ", switchId=" + switchId +
                ", networks=" + networks +
                ", address=" + address +
                '}';
    }
}
