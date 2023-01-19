package io.openrota.service;

import java.util.Map;

import io.openrota.domain.EmailData;
import io.quarkus.mailer.MailTemplate;
import io.smallrye.mutiny.Uni;

public class BaseMailerService {

    protected Uni<Void> send(MailTemplate template, final EmailData emailData, final String subject) {
        MailTemplate.MailTemplateInstance ins = template.to(emailData.getMailTo())
                .subject(subject);
        initData(ins, emailData.getEmailTemplateVariables());
        return ins.send();
    }

    private void initData(MailTemplate.MailTemplateInstance template, Map<String, String> templateVariables) {
        templateVariables.entrySet().forEach(entry -> template.data(entry.getKey(), entry.getValue()));
    }
}
