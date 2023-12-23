package com.example.chapter4.framework.adapters.input;

import com.example.chapter4.application.usecases.RouterNetworkUseCase;
import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.vo.IP;
import com.example.chapter4.domain.vo.Network;
import com.example.chapter4.domain.vo.RouterId;

import java.util.Map;

public abstract class RouterNetworkAdapter {

    protected Router router;
    protected RouterNetworkUseCase routerNetworkUseCase;

    protected Router addNetworkToRouter(Map<String, String> params) {
        var routerId = RouterId.withId(params.get("routerId"));
        var network = new Network(IP.fromAddress(params.get("address")),
                params.get("name"),
                Integer.valueOf(params.get("cidr")));
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    public abstract Router processRequest(Object requestParams);
}
