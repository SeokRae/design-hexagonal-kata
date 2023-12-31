package com.example.chapter2.domain.entity;


import com.example.chapter2.domain.vo.IP;
import com.example.chapter2.domain.vo.Network;
import com.example.chapter2.domain.vo.SwitchId;
import com.example.chapter2.domain.vo.SwitchType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The Switch class represents a network switch.
 */
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

    // 메서드의 의미와는 다르게 하나 추가만 가능
    public Switch addNetwork(Network network) {
        var networks = new ArrayList<>(Collections.singletonList(network));
        networks.add(network);
        return new Switch(this.switchType, this.switchId, networks, this.address);
    }

    public List<Network> getNetworks() {
        return networks;
    }
}
