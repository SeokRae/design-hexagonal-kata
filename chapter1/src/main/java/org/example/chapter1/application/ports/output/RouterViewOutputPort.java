package org.example.chapter1.application.ports.output;

import org.example.chapter1.domain.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRouters();
}
