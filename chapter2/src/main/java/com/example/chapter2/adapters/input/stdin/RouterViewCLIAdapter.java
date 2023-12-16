package com.example.chapter2.adapters.input.stdin;


import com.example.chapter2.adapters.output.file.RouterViewFileAdapter;
import com.example.chapter2.application.ports.input.RouterViewInputPort;
import com.example.chapter2.application.usecases.RouterViewUseCase;
import com.example.chapter2.domain.entity.Router;
import com.example.chapter2.domain.vo.RouterType;

import java.util.List;

public class RouterViewCLIAdapter {

    protected RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}
