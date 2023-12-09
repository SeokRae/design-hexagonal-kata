package org.example.chapter1.domain;

/**
 * 고유하게 식별 가능한 값 객체(RouterId) <br/>
 * - 값 객체는 불변이어야 한다. <br/>
 * - 값 객체는 속성에 관심을 갖는다. <br/>
 * - 값 객체는 동일성을 기반으로 비교되어야 한다. <br/>
 * - 값 객체는 행동이 없어야 한다. <br/>
 * - 값 객체는 투명성을 유지해야 한다. <br/>
 * - 값 객체는 부작용이 없어야 한다. <br/>
 * - 값 객체는 다른 값 객체를 포함할 수 있다. <br/>
 * - 값 객체는 불변이므로 스레드에 안전하다. <br/>
 * - 값 객체는 불변이므로 안전하게 공유할 수 있다.
 */
public class RouterId {
    private final String id;

    // private constructor - use factory method 'of' instead
    private RouterId(String id){
        this.id = id;
    }

    // Factory Method - use this instead of constructor
    public static RouterId of(String id){
        return new RouterId(id);
    }

    @Override
    public String toString() {
        return "RouterId{" +
                "id='" + id + '\'' +
                '}';
    }
}
