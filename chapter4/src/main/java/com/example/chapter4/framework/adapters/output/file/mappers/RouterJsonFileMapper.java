package com.example.chapter4.framework.adapters.output.file.mappers;


import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.entity.Switch;
import com.example.chapter4.domain.vo.*;
import com.example.chapter4.framework.adapters.output.file.json.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RouterJsonFileMapper {

    public static Router toDomain(RouterJson routerJson) {
        var routerId = RouterId.withId(routerJson.getRouterId().toString());
        var routerType = RouterType.valueOf(routerJson.getRouterType().name());
        var switchId = SwitchId.withId(routerJson.getNetworkSwitch().getSwitchId().toString());
        var switchType = SwitchType.valueOf(routerJson.getNetworkSwitch().getSwitchType().toString());
        var ip = IP.fromAddress(routerJson.getNetworkSwitch().getIp().getAddress());
        var networks = getNetworksFromJson(routerJson.getNetworkSwitch().getNetworks());

        var networkSwitch = new Switch(switchId, switchType, networks, ip);

        return new Router(routerType, routerId, networkSwitch);
    }

    public static RouterJson toJson(Router router) {
        UUID routerId = null;
        if (router.getId() != null) {
            routerId = router.getId().getUUID();
        }
        RouterTypeJson routerTypeJson = null;
        if (router.getType() != null) {
            routerTypeJson = RouterTypeJson.valueOf(router.getType().toString());
        }

        UUID switchIdJson = null;
        SwitchTypeJson switchTypeJson = null;
        IPJson ipJson = null;
        List<NetworkJson> networkDataList = null;

        // Check if getNetworkSwitch() returns a non-null value
        if (router.getNetworkSwitch() != null) {
            switchIdJson = router.getNetworkSwitch().getSwitchId().getUUID();
            switchTypeJson = SwitchTypeJson.valueOf(router.getNetworkSwitch().getSwitchType().toString());
            ipJson = IPJson.fromAddress(router.getNetworkSwitch().getAddress().getIPAddress());
            networkDataList = getNetworksFromDomain(router.retrieveNetworks());
        }
        var switchJson = new SwitchJson(switchIdJson, ipJson, switchTypeJson, networkDataList);

        return new RouterJson(routerId, routerTypeJson, switchJson);
    }

    private static List<Network> getNetworksFromJson(List<NetworkJson> networkJson) {
        List<Network> networks = new ArrayList<>();
        networkJson.forEach(json -> {
            var network = new Network(
                    IP.fromAddress(json.getIp().getAddress()),
                    json.getNetworkName(),
                    Integer.valueOf(json.getCidr()));
            networks.add(network);
        });
        return networks;
    }

    private static List<NetworkJson> getNetworksFromDomain(List<Network> networks) {
        List<NetworkJson> networkJsonList = new ArrayList<>();
        networks.forEach(network -> {
            var networkJson = new NetworkJson(
                    IPJson.fromAddress(network.getAddress().getIPAddress()),
                    network.getName(),
                    String.valueOf(network.getCidr())
            );
            networkJsonList.add(networkJson);
        });
        return networkJsonList;
    }

}
