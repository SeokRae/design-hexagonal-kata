package org.example.chapter1.application.usecases;

import org.example.chapter1.domain.Router;

import java.util.List;
import java.util.function.Predicate;

/**
 * This interface represents a use case for retrieving a list of routers based on a filter predicate.
 * 애플리케이션 특화 작업을 추상적으로 처리하는 곳 <br/>
 * - 기술적인 관심사를 직접 다루지 않기 때문에 추상적인 내용을 다루는 곳 <br/>
 * - 도메인 비즈니스 규칙에 기반한 소프트웨어 사용자의 의도와 기능을 표현하는 곳 <br/>
 *
 *
 */
public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}
