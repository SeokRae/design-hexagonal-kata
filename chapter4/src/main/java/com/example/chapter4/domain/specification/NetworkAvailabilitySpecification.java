package com.example.chapter4.domain.specification;


import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.specification.shared.AbstractSpecification;
import com.example.chapter4.domain.vo.IP;
import com.example.chapter4.domain.vo.Network;

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
        var networkSwitch = router.getNetworkSwitch();
        if (networkSwitch == null || networkSwitch.getNetworks() == null)
            return availability;
        for (Network network : networkSwitch.getNetworks()) {
            if (network.getAddress().getIPAddress().equals(address.getIPAddress()) && network.getCidr() == cidr) {
                availability = false;
                break;
            }
        }
        return availability;
    }
}
