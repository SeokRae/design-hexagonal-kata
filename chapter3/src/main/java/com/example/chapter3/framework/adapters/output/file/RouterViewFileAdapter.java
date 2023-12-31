package com.example.chapter3.framework.adapters.output.file;

import com.example.chapter3.application.ports.output.RouterViewOutputPort;
import com.example.chapter3.domain.entity.Router;
import com.example.chapter3.domain.vo.RouterId;
import com.example.chapter3.domain.vo.RouterType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RouterViewFileAdapter implements RouterViewOutputPort {

    private static RouterViewFileAdapter instance;

    private RouterViewFileAdapter() {
    }

    private static List<Router> readFileAsString() {
        List<Router> routers = new ArrayList<>();
        try (Stream<String> stream = new BufferedReader(
                new InputStreamReader(
                        RouterViewFileAdapter.class.getClassLoader().
                                getResourceAsStream("routers.txt"))).lines()) {
            stream.forEach(line -> {
                String[] routerEntry = line.split(";");
                var id = routerEntry[0];
                var type = routerEntry[1];
                Router router = new Router(RouterType.valueOf(type), RouterId.withId(id));
                routers.add(router);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routers;
    }

    public static RouterViewFileAdapter getInstance() {
        if (instance == null) {
            instance = new RouterViewFileAdapter();
        }
        return instance;
    }

    @Override
    public List<Router> fetchRouters() {
        return readFileAsString();
    }
}
