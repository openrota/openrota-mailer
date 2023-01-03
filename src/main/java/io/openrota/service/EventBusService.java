package io.openrota.service;

import io.openrota.domain.EmailData;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.core.eventbus.EventBus;
import io.vertx.mutiny.core.eventbus.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventBusService {

    private static final Logger LOGGER = Logger.getLogger(EventBusService.class);

    @Inject
    EventBus eventBus;

    public Uni sendEvent(EmailData data) {
        return eventBus.request(data.getEmailType(), data)
                .onItem().transform(Message::body);
    }

}
