package com.example.chapter3.framework.adapters.input.stdin;

import com.example.chapter3.application.ports.input.RouterViewInputPort;
import com.example.chapter3.application.usecases.RouterViewUseCase;
import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.vo.RouterType;
import com.example.chapter3.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    private RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}