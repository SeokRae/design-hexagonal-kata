package com.example.chapter4.application.ports.output;


import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
