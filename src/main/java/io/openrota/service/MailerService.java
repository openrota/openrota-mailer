package io.openrota.service;

import io.openrota.domain.EmailData;
import io.smallrye.mutiny.Uni;

public interface MailerService {

    Uni<Void> sendInvite(final EmailData emailData);

    Uni<Void> sendInviteReminder(final EmailData emailData);

    Uni<Void> sendNewAccessReq(final EmailData emailData);

    Uni<Void> sendAccessReqStatus(final EmailData emailData);

    Uni<Void> sendNewResourceReq(final EmailData emailData);

    Uni<Void> sendResourceRequestStatus(final EmailData emailData);

    Uni<Void> sendProjectClosureDueReminder(final EmailData emailData);

    Uni<Void> sendProjectClosureReminder(final EmailData emailData);

    Uni<Void> sendProjectCompletion(final EmailData emailData);
}
