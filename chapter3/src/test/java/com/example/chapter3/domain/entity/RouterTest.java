package com.example.chapter3.domain.entity;

import com.example.chapter3.domain.vo.IP;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.RouterId;
import com.example.chapter3.domain.vo.RouterType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@DisplayName("Router Tests")
class RouterTest {

    @Test
    @DisplayName("Should add network to switch")
    void shouldAddNetworkToSwitch() {
        // Given
        Switch networkSwitch = mock(Switch.class);
        Network network = mock(Network.class);
        Router router = new Router(RouterType.CORE, RouterId.withoutId(), networkSwitch);

        // When
        router.addNetworkToSwitch(network);

        // Then
        verify(networkSwitch).addNetwork(network, router);
    }

    @Test
    @DisplayName("Should create network with given parameters")
    void shouldCreateNetworkWithGivenParameters() {
        // Given
        Router router = new Router(RouterType.CORE, RouterId.withoutId());
        IP address = new IP("192.168.1.1");
        String name = "Network1";
        int cidr = 24;

        // When
        Network network = router.createNetwork(address, name, cidr);

        // Then
        assertThat(network.getAddress()).isEqualTo(address);
        assertThat(network.getName()).isEqualTo(name);
        assertThat(network.getCidr()).isEqualTo(cidr);
    }

    @Test
    @DisplayName("Should retrieve networks from switch")
    void shouldRetrieveNetworksFromSwitch() {
        // Given
        Switch networkSwitch = mock(Switch.class);
        Router router = new Router(RouterType.CORE, RouterId.withoutId(), networkSwitch);

        // When
        router.retrieveNetworks();

        // Then
        verify(networkSwitch).getNetworks();
    }

    @Test
    @DisplayName("Should filter routers by type")
    void shouldFilterRoutersByType() {
        // Given
        Router router = new Router(RouterType.CORE, RouterId.withoutId());

        // When
        Predicate<Router> predicate = Router.filterRouterByType(RouterType.CORE);

        // Then
        assertThat(predicate.test(router)).isTrue();
    }

    @Test
    @DisplayName("Should not filter routers by different type")
    void shouldNotFilterRoutersByDifferentType() {
        // Given
        Router router = new Router(RouterType.CORE, RouterId.withoutId());

        // When
        Predicate<Router> predicate = Router.filterRouterByType(RouterType.EDGE);

        // Then
        assertThat(predicate.test(router)).isFalse();
    }
}