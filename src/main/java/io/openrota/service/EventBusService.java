package io.openrota.service;

import io.openrota.domain.EmailData;
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

    public void sendEvent(EmailData data) {
        eventBus.request(data.getEmailType(), data)
                .onFailure().invoke(e -> LOGGER.error(e.getMessage()))
                .onItem().transform(Message::body)
                .subscribe().with(t -> LOGGER.info("Event " + data.getEmailType() + " sent successfully!"));
    }

}
