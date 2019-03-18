package si.lg.vzorcni.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.eclipse.jetty.http2.api.Session;
import si.lg.vzorcni.storitve.TagZrno;
import si.lg.vzorcni.storitve.UporabnikZrno;
import si.lg.vzorcni.storitve.VprOdgObjektZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
    private VprOdgObjektZrno vprOdgObjektZrno;

    @Operation(description = "Returns list of users.", summary = "Users list", tags = "users", responses = {
            @ApiResponse(responseCode = "200",
                    description = "List of users",
                    content = @Content(
                            array = @ArraySchema(schema = @Schema(implementation = Session.class))),
                    headers = {@Header(name = "X-Total-Count", schema = @Schema(type = "integer"))}
            )})
    @Path("uporabniki/{id}")
    @GET
    public Response vrniUporabnike(@PathParam("id") Integer id) {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        Long uporabnikiCount = uporabnikZrno.pridobiUporabnikiCount(query);

        return Response
                .ok(uporabnikZrno.pridobiUporabnike(query))
                .header("X-Total-Count", uporabnikiCount)
                .build();
    }
}
