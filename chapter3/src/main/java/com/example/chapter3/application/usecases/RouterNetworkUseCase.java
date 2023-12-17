package com.example.chapter3.application.usecases;

import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);
}
