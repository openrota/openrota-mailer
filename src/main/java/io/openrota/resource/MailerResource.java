package io.openrota.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import io.openrota.domain.EmailData;
import io.openrota.service.EventBusService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/mailer")
@ApplicationScoped
public class MailerResource {

    private final EventBusService eventBusService;

    @Inject
    public MailerResource(EventBusService eventBusService) {
        this.eventBusService = eventBusService;
    }


    @POST
    @Path("/mail")
    @Consumes("application/json")
    public Response sendMail(@RequestBody EmailData emailData) {
        eventBusService.sendEvent(emailData);
        return Response.accepted().entity("Email sent!").build();
    }
}
