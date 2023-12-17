package com.example.chapter3.application.ports.output;

import com.example.chapter3.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
