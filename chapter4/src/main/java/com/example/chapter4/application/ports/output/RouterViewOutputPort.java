package com.example.chapter4.application.ports.output;


import com.example.chapter4.domain.entity.Router;

import java.util.List;

public interface RouterViewOutputPort {

    List<Router> fetchRelatedRouters();
}
