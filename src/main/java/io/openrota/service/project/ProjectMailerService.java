package io.openrota.service.project;

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
public class ProjectMailerService extends BaseMailerService {

    @Inject
    @Location("projectClosureDueReminder")
    MailTemplate projectClosureDueReminder;
    @Inject
    @Location("projectClosureReminder")
    MailTemplate projectClosureReminder;

    @Inject
    @Location("projectCompleted")
    MailTemplate projectCompleted;

    @Inject
    @Location("projectExtensionReq")
    MailTemplate projectExtensionReq;

    @Inject
    @Location("projectExtensionReqStatus")
    MailTemplate projectExtensionReqStatus;

    @ConsumeEvent(value = MailerConstants.PROJECT_CLOSURE_DUE_REMINDER)
    public Uni<Void> sendProjectClosureDueReminder(EmailData emailData) {
        return send(projectClosureDueReminder,
                    emailData,
                    MailerConstants.PROJECT_CLOSURE_DUE_REMINDER_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.PROJECT_CLOSURE_REMINDER)
    public Uni<Void> sendProjectClosureReminder(EmailData emailData) {
        return send(projectClosureReminder,
                    emailData,
                    MailerConstants.PROJECT_CLOSURE_REMINDER_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.PROJECT_COMPLETED)
    public Uni<Void> sendProjectCompletion(EmailData emailData) {
        return send(projectCompleted,
                    emailData,
                    MailerConstants.PROJECT_COMPLETED_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.PROJECT_EXTENSION_REQ)
    public Uni<Void> sendProjectExtensionRequest(EmailData emailData) {
        return send(projectExtensionReq,
                    emailData,
                    MailerConstants.PROJECT_EXTENSION_REQ_SUBJECT);
    }

    @ConsumeEvent(value = MailerConstants.PROJECT_EXTENSION_REQ_STATUS)
    public Uni<Void> sendProjectExtensionRequestStatus(EmailData emailData) {
        return send(projectExtensionReqStatus,
                    emailData,
                    MailerConstants.PROJECT_EXTENSION_REQ_STATUS_SUBJECT);
    }
}
