package org.example.chapter1.application.ports.input;

import org.example.chapter1.application.ports.output.RouterViewOutputPort;
import org.example.chapter1.application.usecases.RouterViewUseCase;
import org.example.chapter1.domain.Router;

import java.util.List;
import java.util.function.Predicate;

/**
 * RouterViewInputPort class.
 */
public class RouterViewInputPort implements RouterViewUseCase {

    private final RouterViewOutputPort routerListOutputPort;

    public RouterViewInputPort(RouterViewOutputPort routerViewOutputPort) {
        this.routerListOutputPort = routerViewOutputPort;
    }

    /**
     * Retrieves a list of routers based on the given filter predicate.
     *
     * @param filter The predicate used to filter the routers.
     * @return A list of routers that match the given filter predicate.
     */
    @Override
    public List<Router> getRouters(Predicate<Router> filter) {
        var routers = routerListOutputPort.fetchRouters();
        return Router.retrieveRouter(routers, filter);
    }
}
