package com.example.chapter3.framework.adapters.input.stdin;

import com.example.chapter3.application.ports.input.RouterNetworkInputPort;
import com.example.chapter3.application.usecases.RouterNetworkUseCase;
import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.RouterId;
import com.example.chapter3.framework.adapters.output.file.RouterNetworkFileAdapter;

public class RouterNetworkCLIAdapter {

    RouterNetworkUseCase routerNetworkUseCase;

    public RouterNetworkCLIAdapter() {
        setAdapters();
    }

    public Router addNetwork(RouterId routerId, Network network) {
        return routerNetworkUseCase.addNetworkToRouter(routerId, network);
    }

    private void setAdapters() {
        this.routerNetworkUseCase = new RouterNetworkInputPort(RouterNetworkFileAdapter.getInstance());
    }

}
