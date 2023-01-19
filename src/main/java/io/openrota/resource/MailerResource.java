package io.openrota.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.openrota.domain.EmailData;
import io.openrota.service.EventBusService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

@Path("/mailer")
@ApplicationScoped
@Produces
public class MailerResource {
    private static final Logger LOGGER = Logger.getLogger(MailerResource.class);

    private final EventBusService eventBusService;

    @Inject
    public MailerResource(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }


    @POST
    @Path("/mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMail(@RequestBody EmailData emailData) {
        sendEmailEvent(emailData);
        return Response.accepted().entity("Email sent!").build();
    }

    @POST
    @Path("/multi-mail")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMail(@RequestBody List<EmailData> emailDataList) {
        emailDataList.stream().parallel().forEach(this::sendEmailEvent);
        return Response.accepted().entity("Reminder mails sent!").build();
    }

    private void sendEmailEvent(EmailData emailData) {
        eventBusService.sendEvent(emailData).subscribe()
                .with(item -> LOGGER.info(String.format("Mail %s sent Successfully!", emailData.getEmailType())),
                      LOGGER::error);
    }
}
