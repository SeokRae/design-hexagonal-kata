package com.example.chapter3.domain.specification;

import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.specification.shared.AbstractSpecification;
import com.example.chapter3.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.getRouterType().equals(RouterType.EDGE) || router.getRouterType().equals(RouterType.CORE);
    }
}
