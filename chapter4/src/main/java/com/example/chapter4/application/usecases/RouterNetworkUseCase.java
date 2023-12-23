package com.example.chapter4.application.usecases;


import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.vo.Network;
import com.example.chapter4.domain.vo.RouterId;

public interface RouterNetworkUseCase {

    Router addNetworkToRouter(RouterId routerId, Network network);
}
