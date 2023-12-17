package com.example.chapter3.domain.specification;

import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.specification.shared.AbstractSpecification;
import com.example.chapter3.domain.vo.IP;
import com.example.chapter3.domain.vo.Network;

public class NetworkAvailabilitySpecification extends AbstractSpecification<Router> {

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
            if (network.getAddress().equals(address) && network.getName().equals(name) && network.getCidr() == cidr)
                availability = false;
            break;
        }
        return availability;
    }
}
