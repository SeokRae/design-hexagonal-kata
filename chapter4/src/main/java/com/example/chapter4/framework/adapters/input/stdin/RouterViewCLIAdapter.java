package com.example.chapter4.framework.adapters.input.stdin;


import com.example.chapter4.application.ports.input.RouterViewInputPort;
import com.example.chapter4.application.usecases.RouterViewUseCase;
import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.framework.adapters.output.file.RouterViewFileAdapter;

import java.util.List;

public class RouterViewCLIAdapter {

    RouterViewUseCase routerViewUseCase;

    public RouterViewCLIAdapter() {
        setAdapters();
    }

    public List<Router> obtainRelatedRouters(String type) {
        RouterViewUseCase.RelatedRoutersCommand relatedRoutersCommand = new RouterViewUseCase.RelatedRoutersCommand(type);
        return routerViewUseCase.getRelatedRouters(relatedRoutersCommand);
    }

    private void setAdapters() {
        this.routerViewUseCase = new RouterViewInputPort(RouterViewFileAdapter.getInstance());
    }
}