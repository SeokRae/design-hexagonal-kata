package com.example.chapter4.application.ports.input;

import com.example.chapter4.application.ports.output.RouterViewOutputPort;
import com.example.chapter4.application.usecases.RouterViewUseCase;
import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.service.RouterSearch;
import com.example.chapter4.domain.vo.RouterType;

import java.util.List;

public class RouterViewInputPort implements RouterViewUseCase {

    private final RouterViewOutputPort routerListOutputPort;

    private Router router;

    public RouterViewInputPort(RouterViewOutputPort routerGraphOutputPort) {
        this.routerListOutputPort = routerGraphOutputPort;
    }

    @Override
    public List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand) {
        var type = relatedRoutersCommand.getType();
        var routers = routerListOutputPort.fetchRelatedRouters();
        return fetchRelatedEdgeRouters(type, routers);
    }

    private List<Router> fetchRelatedEdgeRouters(RouterType type, List<Router> routers) {
        return RouterSearch.getRouters(type, routers);
    }
}
