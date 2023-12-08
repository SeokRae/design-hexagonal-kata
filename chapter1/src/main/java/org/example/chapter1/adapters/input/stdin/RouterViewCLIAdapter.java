package org.example.chapter1.adapters.input.stdin;

import org.example.chapter1.adapters.input.output.file.RouterViewFileAdapter;
import org.example.chapter1.application.ports.input.RouterViewInputPort;
import org.example.chapter1.application.usecases.RouterViewUseCase;
import org.example.chapter1.domain.Router;
import org.example.chapter1.domain.RouterType;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter(){
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        return routerViewUseCase.getRouters(
                Router.filterRouterByType(RouterType.valueOf(type)));
    }

    private void setAdapters(){
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance("routers.txt"));
    }
}
