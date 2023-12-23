package com.example.chapter4.framework.adapters.input.stdin;


import com.example.chapter4.application.usecases.RouterNetworkUseCase;
import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.specification.CIDRSpecification;
import com.example.chapter4.framework.adapters.input.RouterNetworkAdapter;
import com.example.chapter4.framework.adapters.output.file.mappers.RouterJsonFileMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RouterNetworkCLIAdapter extends RouterNetworkAdapter {

    public RouterNetworkCLIAdapter(RouterNetworkUseCase routerNetworkUseCase) {
        this.routerNetworkUseCase = routerNetworkUseCase;
    }

    @Override
    public Router processRequest(Object requestParams) {
        var params = stdinParams(requestParams);
        router = this.addNetworkToRouter(params);
        ObjectMapper mapper = new ObjectMapper();
        try {
            var routerJson = mapper.writeValueAsString(RouterJsonFileMapper.toJson(router));
            System.out.println(routerJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return router;
    }

    private Map<String, String> stdinParams(Object requestParams) {
        Map<String, String> params = new HashMap<>();
        if (requestParams instanceof Scanner scanner) {
            System.out.println("Please inform the Router ID (up to 36 characters):");
            var routerId = scanner.nextLine();
            if (routerId.length() > 36) {
                System.out.println("Input length is too long, it's been truncated to 36 characters.");
                routerId = routerId.substring(0, 36);
            }
            params.put("routerId", routerId);
            System.out.println("Please inform the IP address:");
            var address = scanner.nextLine();
            params.put("address", address);
            System.out.println("Please inform the Network Name:");
            var name = scanner.nextLine();
            params.put("name", name);
            System.out.println("Please inform the CIDR:");
            try {
                var cidr = Integer.parseInt(scanner.nextLine());
                if (cidr < CIDRSpecification.MINIMUM_ALLOWED_CIDR) {
                    throw new IllegalArgumentException("CIDR cannot be less than " + CIDRSpecification.MINIMUM_ALLOWED_CIDR);
                }
                params.put("cidr", String.valueOf(cidr));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("CIDR needs to be an integer");
            }
        } else {
            throw new IllegalArgumentException("Request with invalid parameters");
        }
        System.out.println("params = " + params);
        return params;
    }
}
