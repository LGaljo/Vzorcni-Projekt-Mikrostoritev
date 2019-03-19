package si.lg.vzorcni.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.jetty.http2.api.Session;
import si.lg.vzorcni.entitete.Uporabnik;
import si.lg.vzorcni.entitete.Vprasanje;
import si.lg.vzorcni.storitve.TagZrno;
import si.lg.vzorcni.storitve.UporabnikZrno;
import si.lg.vzorcni.storitve.OdgovorZrno;
import si.lg.vzorcni.storitve.VprasanjeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class API_endpoints {
    @Context
    protected UriInfo uriInfo;

    @Inject
    private TagZrno tagZrno;

    @Inject
    private UporabnikZrno uporabnikZrno;

    @Inject
    private OdgovorZrno odgovorZrno;

    @Inject
    private VprasanjeZrno vprasanjeZrno;

    @Operation(description = "Returns a user.", summary = "Users", tags = "users", responses = {
            @ApiResponse(responseCode = "200",
                    description = "User",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Session.class)))
            )})
    @Path("uporabniki/{id}")
    @GET
    public Response vrniUporabnika(@PathParam("id") Integer id) {
        Uporabnik uporabnik = uporabnikZrno.pridobiUporabnika(id);

        return Response
                .ok(uporabnik)
                .build();
    }

    @Operation(description = "Returns list of questions.", summary = "Questions", tags = "questions", responses = {
            @ApiResponse(responseCode = "200",
                    description = "Question",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Session.class)))
            )})
    @Path("vprasanje/{tag_id}")
    @GET
    public Response pridobiVprasanje(QueryParameters queryParameters, @PathParam("tag_id") Integer tagID) {
        List<Vprasanje> vprasanja = vprasanjeZrno.pridobiVprasanjeTAG(tagZrno.pridobiTag(tagID));

        return Response
                .status(Response.Status.OK)
                .entity(vprasanja)
                .build();
    }
}
