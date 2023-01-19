package io.openrota.service.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.openrota.constants.MailerConstants;
import io.openrota.domain.EmailData;
import io.openrota.service.BaseMailerService;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.Location;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class ResourceMailerService extends BaseMailerService {

    @Inject
    @Location("newResourceReq")
    MailTemplate newResourceReq;
    @Inject
    @Location("resourceRequestStatus")
    MailTemplate resourceRequestStatus;

    @ConsumeEvent(value = MailerConstants.NEW_RESOURCE_REQ)
    public Uni<Void> sendNewResourceReq(EmailData emailData) {
        return send(newResourceReq,
                    emailData,
                    MailerConstants.NEW_RESOURCE_REQ_SUBJECT);    }

    @ConsumeEvent(value = MailerConstants.RESOURCE_REQUEST_STATUS)
    public Uni<Void> sendResourceRequestStatus(EmailData emailData) {
        return send(resourceRequestStatus,
                    emailData,
                    MailerConstants.RESOURCE_REQUEST_STATUS_SUBJECT);
    }
}
