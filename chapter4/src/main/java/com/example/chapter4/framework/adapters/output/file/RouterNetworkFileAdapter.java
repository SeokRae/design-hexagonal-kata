package com.example.chapter4.framework.adapters.output.file;


import com.example.chapter4.application.ports.output.RouterNetworkOutputPort;
import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.vo.RouterId;
import com.example.chapter4.framework.adapters.output.file.json.RouterJson;
import com.example.chapter4.framework.adapters.output.file.mappers.RouterJsonFileMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

public class RouterNetworkFileAdapter implements RouterNetworkOutputPort {

    private static RouterNetworkFileAdapter instance;
    private final InputStream resource;
    private final ObjectMapper objectMapper;
    private List<RouterJson> routers;

    private RouterNetworkFileAdapter() {
        this.objectMapper = new ObjectMapper();
        this.resource = getClass().
                getClassLoader().
                getResourceAsStream("inventory.json");
        readJsonFile();
    }

    public static RouterNetworkFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterNetworkFileAdapter();
        }
        return instance;
    }

    @Override
    public Router fetchRouterById(RouterId routerId) {
        Router router = new Router();
        for (RouterJson routerJson : routers) {
            if (routerJson.getRouterId().equals(routerId.getUUID())) {
                router = RouterJsonFileMapper.toDomain(routerJson);
                System.out.println("routerJson = " + router);
                break;
            }
        }
        return router;
    }

    @Override
    public boolean persistRouter(Router router) {
        var routerJson = RouterJsonFileMapper.toJson(router);
        try {
            String localDir = Paths.get("").toAbsolutePath().toString();
            File file = new File(localDir + "/inventory.json");
            file.delete();
            objectMapper.writeValue(file, routerJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private void readJsonFile() {
        try {
            this.routers = objectMapper.
                    readValue(
                            resource,
                            new TypeReference<>() {
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
