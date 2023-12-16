package com.example.chapter2.domain.specification;

import com.example.chapter2.domain.entity.Router;
import com.example.chapter2.domain.specification.shared.AbstractSpecification;
import com.example.chapter2.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
