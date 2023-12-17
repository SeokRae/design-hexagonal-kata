package com.example.chapter3.application.ports.input;

import com.example.chapter3.application.ports.output.RouterViewOutputPort;
import com.example.chapter3.application.usecases.RouterViewUseCase;
import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.service.RouterSearch;

import java.util.List;
import java.util.function.Predicate;

public class RouterViewInputPort implements RouterViewUseCase {

    private final RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return RouterSearch.retrieveRouter(routers, filter);
    }
}
