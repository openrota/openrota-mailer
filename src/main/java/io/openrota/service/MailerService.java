package io.openrota.service;

import io.openrota.domain.EmailData;
import io.smallrye.mutiny.Uni;

public interface MailerService {

    Uni<String> send(final EmailData emailData);
}
