package com.example.chapter2.domain.service;

import com.example.chapter2.domain.entity.Router;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RouterSearch {

    public static List<Router> retrieveRouter(List<Router> routers, Predicate<Router> predicate) {
        return routers.stream()
                .filter(predicate)
                .collect(Collectors.<Router>toList());
    }
}
