package io.openrota.service.access;

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
public class AccessMailerService extends BaseMailerService {

    @Inject
    @Location("openrotaInvitation")
    MailTemplate openrotaInvitation;
    @Inject
    @Location("inviteExpireReminder")
    MailTemplate invitationExpiration;
    @Inject
    @Location("newAccessReq")
    MailTemplate newAccessReq;
    @Inject
    @Location("accessReqStatus")
    MailTemplate accessReqStatus;

    @ConsumeEvent(value = MailerConstants.OPENROTA_INVITATION)
    public Uni<Void> sendInvite(final EmailData emailData) {
        return send(openrotaInvitation,
                    emailData,
                    MailerConstants.OPENROTA_INVITATION_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.INVITATION_EXPIRATION)
    public Uni<Void> sendInviteReminder(EmailData emailData) {
        return send(invitationExpiration,
                    emailData,
                    MailerConstants.INVITATION_EXPIRATION_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.NEW_ACCESS_REQ)
    public Uni<Void> sendNewAccessReq(EmailData emailData) {
        return send(newAccessReq,
                    emailData,
                    MailerConstants.NEW_ACCESS_REQ_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.ACCESS_REQ_STATUS)
    public Uni<Void> sendAccessReqStatus(EmailData emailData) {
        return send(accessReqStatus,
                    emailData,
                    MailerConstants.ACCESS_REQ_STATUS_SUBJECT);
    }
}
