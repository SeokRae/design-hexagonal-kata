package com.example.chapter2.domain.entity;

import com.example.chapter2.domain.vo.IP;
import com.example.chapter2.domain.vo.Network;
import com.example.chapter2.domain.vo.RouterId;
import com.example.chapter2.domain.vo.RouterType;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RouterTest {

    @Mock
    Switch networkSwitch;

    @Test
    void shouldFilterCoreRouterWhenRouterTypeIsCore() {
        Router router = new Router(RouterType.CORE, RouterId.withId("1"));
        assertTrue(Router.filterRouterByType(RouterType.CORE).test(router));
    }

    @Test
    void shouldFilterEdgeRouterWhenRouterTypeIsEdge() {
        Router router = new Router(RouterType.EDGE, RouterId.withId("1"));
        assertTrue(Router.filterRouterByType(RouterType.EDGE).test(router));
    }

    @Test
    void shouldAddNetworkToSwitch() {
        Router router = new Router(RouterType.CORE, RouterId.withId("1"));
        Network network = new Network(IP.withAddress("192.168.0.1"), "Network1", 24);
        when(networkSwitch.addNetwork(network)).thenReturn(networkSwitch);
        router.addNetworkToSwitch(network);
        verify(networkSwitch, times(1)).addNetwork(network);
    }

    @Test
    void shouldCreateNetwork() {
        // given
        Router router = new Router(RouterType.CORE, RouterId.withId("1"));
        // when
        Network network = router.createNetwork(IP.withAddress("192.168.0.1"), "Network1", 24);
        // then
        assertThat(network).isNotNull();
    }

    @Test
    void shouldRetrieveNetworksFromSwitch() {
        // given
        Router router = new Router(RouterType.CORE, RouterId.withId("1"));
        // when
        when(networkSwitch.getNetworks()).thenReturn(
                List.of(
                        new Network(IP.withAddress("192.168.0.1"), "Network1", 24))
        );
        // then
        assertThat(router.retrieveNetworks()).hasSize(1);
    }
}