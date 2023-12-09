package org.example.chapter1.domain;

import java.util.function.Predicate;


/**
 * 속성에 관심을 갖는 값 객체 <br/>
 * - 값 객체는 식별자를 갖지 않는다. <br/>
 * - 값 객체는 불변이다. <br/>
 * - 값 객체는 투명성을 유지해야 한다. <br/>
 * - 값 객체는 부작용이 없다. <br/>
 * - 값 객체는 다른 값 객체를 포함할 수 있다. <br/>
 * - 값 객체는 스레드에 안전하다. <br/>
 * - 값 객체는 공유할 수 있다. <br/>
 * - 값 객체는 불변이므로 스레드에 안전하다. <br/>
 * - 값 객체는 불변이므로 공유할 수 있다. <br/>
 * - 값 객체는 불변이므로 동일성을 기반으로 비교할 수 있다. <br/>
 * - 값 객체는 불변이므로 동등성을 기반으로 비교할 수 있다. <br/>
 * - 값 객체는 불변이므로 동일성을 기반으로 해시코드를 생성할 수 있다. <br/>
 * - 값 객체는 불변이므로 toString() 메서드를 재정의할 수 있다. <br/>
 * - 값 객체는 불변이므로 equals() 메서드를 재정의할 수 있다. <br/>
 * - 값 객체는 불변이므로 hashCode() 메서드를 재정의할 수 있다. <br/>
 * - 값 객체는 불변이므로 clone() 메서드를 재정의할 수 있다. <br/>
 * - 값 객체는 불변이므로 finalize() 메서드를 재정의할 수 있다. <br/>
 * - 값 객체는 불변이므로 Comparable 인터페이스를 구현할 수 있다. <br/>
 * - 값 객체는 불변이므로 Serializable 인터페이스를 구현할 수 있다. <br/>
 * - 값 객체를 이용해 엔티티 객체를 구성할 수 있다. <br/>
 */
public enum RouterType {
    EDGE(RouterType::isEdge),
    CORE(RouterType::isCore);

    private final Predicate<Router> filter;

    RouterType(Predicate<Router> filter) {
        this.filter = filter;
    }

    /**
     * Retrieves the filter for the routers based on the router type.
     *
     * @return A predicate that filters the routers based on the router type.
     */
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
