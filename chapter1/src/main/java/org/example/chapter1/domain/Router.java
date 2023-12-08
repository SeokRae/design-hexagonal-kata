package org.example.chapter1.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Router {

    private final RouterType routerType;

    private final RouterId routerId;

    public Router(RouterType routerType, RouterId routerId) {
        this.routerType = routerType;
        this.routerId = routerId;
    }

    /**
     * Filters the routers based on the given router type.
     *
     * @param routerType The router type to filter by.
     * @return A predicate that filters the routers based on the given router type.
     */
    public static Predicate<Router> filterRouterByType(RouterType routerType){
        return routerType.getFilter();
    }

    /**
     * Retrieves a list of routers based on the given predicate.
     *
     * @param routers   The list of routers to filter.
     * @param predicate The predicate used to filter the routers.
     * @return A list of routers that match the given predicate.
     */
    public static List<Router> retrieveRouter(List<Router> routers, Predicate<Router> predicate){
        return routers.stream()
                .filter(predicate)
                // collect(Collectors.<Router>toList()) 단순 조회 라면 수정불가 리스트를 반환
                .toList();
    }

    public RouterType getRouterType(){
        return routerType;
    }

    public RouterId getRouterId() {
        return routerId;
    }

    @Override
    public String toString(){
        return "Router{" +
                "routerType=" + routerType +
                ", routerId=" + routerId +
                '}';
    }
}
