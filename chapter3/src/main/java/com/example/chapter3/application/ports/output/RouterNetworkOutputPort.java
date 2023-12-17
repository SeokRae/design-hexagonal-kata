package com.example.chapter3.application.ports.output;

import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.vo.RouterId;

public interface RouterNetworkOutputPort {
    Router fetchRouterById(RouterId routerId);

    boolean persistRouter(Router router);
}
