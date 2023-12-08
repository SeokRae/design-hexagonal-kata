package org.example.chapter1.domain;

import java.util.function.Predicate;

public enum RouterType {
    EDGE(RouterType::isEdge),
    CORE(RouterType::isCore);

    private final Predicate<Router> filter;

    RouterType(Predicate<Router> filter) {
        this.filter = filter;
    }

    public Predicate<Router> getFilter() {
        return filter;
    }

    private static boolean isCore(Router router){
        return router.getRouterType() == RouterType.CORE;
    }

    private static boolean isEdge(Router router){
        return router.getRouterType() == RouterType.EDGE;
    }

}
