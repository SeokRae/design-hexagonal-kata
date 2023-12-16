package com.example.chapter2.domain.specification;


import com.example.chapter2.domain.entity.Router;
import com.example.chapter2.domain.specification.shared.AbstractSpecification;
import com.example.chapter2.domain.vo.IP;
import com.example.chapter2.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Router> {

    // final 필드 수정
    private final IP address;
    private final String name;
    private final int cidr;

    public NetworkAvailabilitySpecification(IP address, String name, int cidr) {
        this.address = address;
        this.name = name;
        this.cidr = cidr;
    }

    @Override
    public boolean isSatisfiedBy(Router router) {
        return router != null && isNetworkAvailable(router);
    }

    private boolean isNetworkAvailable(Router router) {
        var availability = true;
        for (Network network : router.retrieveNetworks()) {
            if (network.getAddress().equals(address) && network.getName().equals(name) && network.getCidr() == cidr) {
                availability = false;
                break;
            }
            break;
        }
        return availability;
    }
}
