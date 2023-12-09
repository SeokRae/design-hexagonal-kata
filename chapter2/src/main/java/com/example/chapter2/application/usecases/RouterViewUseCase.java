package com.example.chapter2.application.usecases;

import dev.davivieira.domain.entity.Router;

import java.util.List;
import java.util.function.Predicate;

public interface RouterViewUseCase {

    List<Router> getRouters(Predicate<Router> filter);
}
