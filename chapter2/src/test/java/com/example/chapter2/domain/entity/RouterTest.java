package com.example.chapter2.domain.entity;

import com.example.chapter2.domain.vo.IP;
import com.example.chapter2.domain.vo.Network;
import com.example.chapter2.domain.vo.RouterId;
import com.example.chapter2.domain.vo.RouterType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RouterTest {

    @Mock
    private Switch networkSwitch;

    @InjectMocks
    private Router router;

    @Test
    void shouldFilterCoreRouterWhenRouterTypeIsCore() {
        String id = UUID.randomUUID().toString();
        Router router = new Router(RouterType.CORE, RouterId.withId(id));
        assertTrue(Router.filterRouterByType(RouterType.CORE).test(router));
    }

    @Test
    void shouldFilterEdgeRouterWhenRouterTypeIsEdge() {
        String id = UUID.randomUUID().toString();
        Router router = new Router(RouterType.EDGE, RouterId.withId(id));
        assertTrue(Router.filterRouterByType(RouterType.EDGE).test(router));
    }

    @Test
    void shouldCreateNetwork() {
        // given
        String id = UUID.randomUUID().toString();
        Router router = new Router(RouterType.CORE, RouterId.withId(id));
        // when
        Network network = router.createNetwork(IP.withAddress("192.168.0.1"), "Network1", 24);
        // then
        assertThat(network).isNotNull();
    }

}