package com.example.chapter2.application.ports.output;

import com.example.chapter2.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
