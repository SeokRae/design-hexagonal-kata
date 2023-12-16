package com.example.chapter2.domain.service;

import com.example.chapter2.domain.entity.Router;
import com.example.chapter2.domain.specification.CIDRSpecification;
import com.example.chapter2.domain.specification.NetworkAmountSpecification;
import com.example.chapter2.domain.specification.NetworkAvailabilitySpecification;
import com.example.chapter2.domain.specification.RouterTypeSpecification;
import com.example.chapter2.domain.vo.Network;

public class NetworkOperation {

    public static Router createNewNetwork(Router router, Network network) {
        var availabilitySpec = new NetworkAvailabilitySpecification(network.getAddress(), network.getName(), network.getCidr());
        var cidrSpec = new CIDRSpecification();
        var routerTypeSpec = new RouterTypeSpecification();
        var amountSpec = new NetworkAmountSpecification();

        if(cidrSpec.isSatisfiedBy(network.getCidr()))
            throw new IllegalArgumentException("CIDR is below "+CIDRSpecification.MINIMUM_ALLOWED_CIDR);

        if(!availabilitySpec.isSatisfiedBy(router))
            throw new IllegalArgumentException("Address already exist");

        if(amountSpec.and(routerTypeSpec).isSatisfiedBy(router)) {
            Network newNetwork = router.createNetwork(network.getAddress(), network.getName(), network.getCidr());
            router.addNetworkToSwitch(newNetwork);
        }
        return router;
    }
}
