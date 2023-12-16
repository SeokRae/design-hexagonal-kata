package com.example.chapter2.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@DisplayName("Network")
class NetworkTest {

    @DisplayName("Should create Network with valid IP, name and CIDR")
    @CsvSource(value = "192.168.0.1,Network1,24")
    @ParameterizedTest(name = "{index} => ip:{0}, name:{1}, cidr:{2}")
    void shouldCreateNetworkWithValidIPNameAndCIDR(String ipStr, String name, int cidr) {
        // given
        IP ip = IP.withAddress(ipStr);
        Network actualNetwork = new Network(ip, name, cidr);
        // then
        Network expectedNetwork = new Network(ip, name, cidr);
        assertThat(actualNetwork)
                .usingRecursiveComparison()
                .isEqualTo(expectedNetwork);
    }

    @DisplayName("Should throw exception when CIDR is less than 1")
    @CsvSource(value = "192.168.0.1,Network1,0")
    @ParameterizedTest(name = "{index} => ip:{0}, name:{1}, cidr:{2}")
    void shouldThrowExceptionWhenCIDRIsLessThanOne(String ipStr, String name, int cidr) {
        IP ip = IP.withAddress(ipStr);
        assertThrowsExactly(IllegalArgumentException.class, () -> new Network(ip, name, cidr));
    }

    @DisplayName("Should throw exception when CIDR is more than 32")
    @CsvSource(value = "192.168.0.1,Network1,0")
    @ParameterizedTest(name = "{index} => ip:{0}, name:{1}, cidr:{2}")
    void shouldThrowExceptionWhenCIDRIsMoreThanThirtyTwo(String ipStr, String name, int cidr) {
        IP ip = IP.withAddress(ipStr);
        assertThrowsExactly(IllegalArgumentException.class, () -> new Network(ip, name, cidr));
    }
}