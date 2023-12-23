package com.example.chapter4.domain.entity;

import com.example.chapter4.domain.vo.IP;
import com.example.chapter4.domain.vo.Network;
import com.example.chapter4.domain.vo.SwitchId;
import com.example.chapter4.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.List;


public class Switch {

    private final SwitchId switchId;
    private final SwitchType switchType;
    private final List<Network> networks;
    private final IP address;

    public Switch(SwitchId switchId, SwitchType switchType, List<Network> networks, IP address) {
        this.switchId = switchId;
        this.switchType = switchType;
        this.networks = networks;
        this.address = address;
    }

    public Switch addNetwork(Network network, Router router) {

        System.out.println("network = " + network);
        System.out.println("router = " + router);
        List<Network> newNetworks = new ArrayList<>(router.retrieveNetworks());
        newNetworks.add(network);

        return new Switch(this.switchId, this.switchType, newNetworks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public SwitchId getSwitchId() {
        return switchId;
    }

    public SwitchType getSwitchType() {
        return switchType;
    }

    public IP getAddress() {
        return address;
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
