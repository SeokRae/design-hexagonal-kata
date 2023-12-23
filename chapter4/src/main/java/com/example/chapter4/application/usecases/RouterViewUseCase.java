package com.example.chapter4.application.usecases;

import com.example.chapter4.domain.entity.Router;
import com.example.chapter4.domain.vo.RouterType;

import java.util.List;

public interface RouterViewUseCase {

    List<Router> getRelatedRouters(RelatedRoutersCommand relatedRoutersCommand);

    class RelatedRoutersCommand {

        private final RouterType type;

        public RelatedRoutersCommand(String type) {
            this.type = RouterType.valueOf(type);
        }

        public RouterType getType() {
            return type;
        }
    }
}
