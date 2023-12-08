package org.example.chapter1.application.usecases;

import org.example.chapter1.domain.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}
