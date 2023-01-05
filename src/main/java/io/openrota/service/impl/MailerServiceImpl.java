package io.openrota.service.impl;

import java.util.Map;

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
    @Inject
    @Location("newResourceReq")
    MailTemplate newResourceReq;
    @Inject
    @Location("resourceRequestStatus")
    MailTemplate resourceRequestStatus;
    @Inject
    @Location("projectClosureDueReminder")
    MailTemplate projectClosureDueReminder;
    @Inject
    @Location("projectClosureReminder")
    MailTemplate projectClosureReminder;

    @Inject
    @Location("projectCompleted")
    MailTemplate projectCompleted;

    @Override
    @ConsumeEvent(value = MailerConstants.OPENROTA_INVITATION)
    public Uni<Void> sendInvite(final EmailData emailData) {
        return send(openrotaInvitation,
                    emailData,
                    MailerConstants.OPENROTA_INVITATION_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.INVITATION_EXPIRATION)
    public Uni<Void> sendInviteReminder(EmailData emailData) {
        return send(invitationExpiration,
                    emailData,
                    MailerConstants.INVITATION_EXPIRATION_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.NEW_ACCESS_REQ)
    public Uni<Void> sendNewAccessReq(EmailData emailData) {
        return send(newAccessReq,
                    emailData,
                    MailerConstants.NEW_ACCESS_REQ_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.ACCESS_REQ_STATUS)
    public Uni<Void> sendAccessReqStatus(EmailData emailData) {
        return send(accessReqStatus,
                    emailData,
                    MailerConstants.ACCESS_REQ_STATUS_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.NEW_RESOURCE_REQ)
    public Uni<Void> sendNewResourceReq(EmailData emailData) {
        return send(newResourceReq,
                    emailData,
                    MailerConstants.NEW_RESOURCE_REQ_SUBJECT);    }

    @Override
    @ConsumeEvent(value = MailerConstants.RESOURCE_REQUEST_STATUS)
    public Uni<Void> sendResourceRequestStatus(EmailData emailData) {
        return send(resourceRequestStatus,
                    emailData,
                    MailerConstants.RESOURCE_REQUEST_STATUS_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.PROJECT_CLOSURE_DUE_REMINDER)
    public Uni<Void> sendProjectClosureDueReminder(EmailData emailData) {
        return send(projectClosureDueReminder,
                    emailData,
                    MailerConstants.PROJECT_CLOSURE_DUE_REMINDER_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.PROJECT_CLOSURE_REMINDER)
    public Uni<Void> sendProjectClosureReminder(EmailData emailData) {
        return send(projectClosureReminder,
                    emailData,
                    MailerConstants.PROJECT_CLOSURE_REMINDER_SUBJECT);
    }

    @Override
    @ConsumeEvent(value = MailerConstants.PROJECT_COMPLETED)
    public Uni<Void> sendProjectCompletion(EmailData emailData) {
        return send(projectCompleted,
                    emailData,
                    MailerConstants.PROJECT_COMPLETED_SUBJECT);
    }

    private Uni<Void> send(MailTemplate template, final EmailData emailData, final String subject) {
        MailTemplate.MailTemplateInstance ins = template.to(emailData.getMailTo())
                .subject(subject);
        initData(ins, emailData.getEmailTemplateVariables());
        return ins.send();
    }

    private void initData(MailTemplate.MailTemplateInstance template, Map<String, String> templateVariables) {
        templateVariables.entrySet().forEach(entry -> template.data(entry.getKey(), entry.getValue()));
    }
}
