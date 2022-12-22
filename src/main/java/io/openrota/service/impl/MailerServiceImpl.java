package io.openrota.service.impl;

import io.openrota.constants.MailerConstants;
import io.openrota.domain.EmailData;
import io.openrota.service.MailerService;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.Location;
import io.quarkus.vertx.ConsumeEvent;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailerServiceImpl implements MailerService {

    private static final Logger LOGGER = Logger.getLogger(MailerServiceImpl.class);

    @Inject
    @Location("openRotaInvitation")
    MailTemplate openRotaInvitation;

    @Override
    @ConsumeEvent(value = MailerConstants.OPENROTA_INVITATION)
    public Uni<String> send(final EmailData emailData) {
        openRotaInvitation.to(emailData.getMailTo())
            .subject(MailerConstants.OPENROTA_INVITATION_SUBJECT)
            .data("invitationLink", emailData.getEmailTemplateVariables().get("invitationLink"))
            .send()
            .subscribe()
            .with(t -> LOGGER.info("Mail sent to " + emailData.getMailTo()));
        return Uni.createFrom().item(() -> "Mail sent to " + emailData.getMailTo());
    }
}
