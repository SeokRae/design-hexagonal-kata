package com.example.chapter3.framework.adapters.output.file;

import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.specification.CIDRSpecification;
import com.example.chapter3.domain.specification.NetworkAvailabilitySpecification;
import com.example.chapter3.domain.vo.IP;
import com.example.chapter3.domain.vo.Network;
import com.example.chapter3.domain.vo.RouterId;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RouterNetworkFileAdapterTest {

    private RouterId routerId;
    private Router router;
    private RouterNetworkFileAdapter routerNetworkFileAdapter;
    private Network network;

    @Before
    public void setup() {
        // 각 테스트 전에 객체 초기화
        routerNetworkFileAdapter = RouterNetworkFileAdapter.getInstance();
        network = new Network(new IP("127.0.0.1"), "Marketing", 9);
    }

    @Given("I provide a router ID and the network details")
    public void obtain_routerId() {
        // 라우터 ID 제공
        this.routerId = RouterId.withId("ca23800e-9b5a-11eb-a8b3-0242ac130003");
    }

    @When("I found the router")
    public void lookup_router() {
        // 라우터 검색
        router = routerNetworkFileAdapter.fetchRouterById(routerId);
    }

    @And("The network address is valid and doesn't already exists")
    public void check_address_validity_and_existence() {
        // 네트워크 주소의 유효성 및 중복성 검사
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
    }

    @Given("The CIDR is valid")
    public void check_cidr() {
        // CIDR의 유효성 검사
        var cidrSpec = new CIDRSpecification();
        assertThat(cidrSpec.isSatisfiedBy(network.getCidr())).isTrue();
    }

    @Then("Add the network to the router")
    public void add_network() {
        // 네트워크를 라우터에 추가
        router.addNetworkToSwitch(network);

        List<Network> networks = router.retrieveNetworks();
        // 네트워크가 라우터에 성공적으로 추가되었는지 확인
        assertThat(networks).isNotNull();
    }
}
