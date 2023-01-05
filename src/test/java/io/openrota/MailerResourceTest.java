package io.openrota;

import io.openrota.constants.MailerConstants;
import io.openrota.domain.EmailData;
import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.json.Json;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class MailerResourceTest {

    private static final String TO = "foo@quarkus.io";

    @Test
    void testMail() {
        EmailData emailData = EmailData.builder()
                .emailType(MailerConstants.OPENROTA_INVITATION)
                .mailTo(TO)
                .emailTemplateVariables(Map.of("invitationLink", "https://www.foo.bar"))
                .build();
        // call a REST endpoint that sends email
        given()
                .contentType("application/json")
                .body(Json.encode(emailData))
                .when()
                .post("/mailer/mail")
                .then()
                .statusCode(202);
    }
}
