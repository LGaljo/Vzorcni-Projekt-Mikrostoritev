package v1;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;

@OpenAPIDefinition(info = @Info(title = "Rest API", version = "v1",
        contact = @Contact(),
        license = @License(), description = "REST API for collecting user responses"),
        security = @SecurityRequirement(name = "openid-connect"),
        servers = @Server(url ="http://localhost:8080/v1"))
@ApplicationPath("v1")
public class VzorcniProjektApp extends javax.ws.rs.core.Application {
}