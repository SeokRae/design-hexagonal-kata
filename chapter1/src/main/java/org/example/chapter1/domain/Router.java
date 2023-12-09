package org.example.chapter1.domain;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * 표현력 있는 엔티티(Router) <br/>
 * - 엔티티는 식별자를 갖는다. <br/>
 * - 엔티티는 불변이 아니다. <br/>
 * - 엔티티는 행동을 갖는다. <br/>
 * - 엔티티는 투명성을 유지해야 한다. <br/>
 * - 엔티티는 부작용이 있을 수 있다. <br/>
 * - 엔티티는 다른 엔티티를 포함할 수 있다. <br/>
 * - 엔티티는 스레드에 안전하지 않다. <br/>
 * - 엔티티는 공유해서는 안된다.
 * - 엔티티는 불변이 아니므로 스레드에 안전하지 않다.
 * - 엔티티는 불변이 아니므로 공유해서는 안된다.
 * - 엔티티는 불변이 아니므로 동일성을 기반으로 비교할 수 없다.
 * - 엔티티는 불변이 아니므로 동등성을 기반으로 비교할 수 없다.
 * - 엔티티는 불변이 아니므로 동일성을 기반으로 해시코드를 생성할 수 없다.
 * - 엔티티는 불변이 아니므로 toString() 메서드를 재정의할 수 없다.
 * - 엔티티는 불변이 아니므로 equals() 메서드를 재정의할 수 없다.
 * - 엔티티는 불변이 아니므로 hashCode() 메서드를 재정의할 수 없다.
 * - 엔티티는 불변이 아니므로 clone() 메서드를 재정의할 수 없다.
 * - 엔티티는 불변이 아니므로 finalize() 메서드를 재정의할 수 없다.
 * - 엔티티는 불변이 아니므로 Comparable 인터페이스를 구현할 수 없다.
 * - 엔티티는 불변이 아니므로 Serializable 인터페이스를 구현할 수 없다.
 * - 연속성과 정체성은 엔티티를 결정하는 요소이다.
 */
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
