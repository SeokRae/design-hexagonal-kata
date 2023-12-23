package com.example.chapter4.domain.specification;


import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.specification.shared.AbstractSpecification;
import com.example.chapter4.domain.vo.RouterType;

public class RouterTypeSpecification extends AbstractSpecification<Router> {
    @Override
    public boolean isSatisfiedBy(Router router) {
        return router.isType(RouterType.EDGE);
    }
}
