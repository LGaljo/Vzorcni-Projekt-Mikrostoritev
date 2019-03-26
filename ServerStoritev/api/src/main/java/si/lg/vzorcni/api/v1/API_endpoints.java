package si.lg.vzorcni.api.v1;

import com.kumuluz.ee.rest.beans.QueryParameters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import si.lg.vzorcni.entitete.OdgovorDTO;
import si.lg.vzorcni.entitete.Tag;
import si.lg.vzorcni.entitete.Vprasanje;
import si.lg.vzorcni.storitve.TagZrno;
import si.lg.vzorcni.storitve.OdgovorZrno;
import si.lg.vzorcni.storitve.VprasanjeZrno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
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
    private OdgovorZrno odgovorZrno;

    @Inject
    private VprasanjeZrno vprasanjeZrno;

    @Operation(
            description = "Get list of questions by tag.",
            summary = "questions",
            tags = "questions",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Question",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Vprasanje.class)
                                    )
                            )),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No questions found"
                    )})
    @Path("vprasanje/{tag_id}")
    @GET
    public Response pridobiVprasanje(
            @Parameter(description = "Query parameters used to sort entities.", required = true)
                    QueryParameters queryParameters,
            @Parameter(description = "This is an ID of a tag which is then matched with question.", required = true)
            @PathParam("tag_id") Integer tagID) throws NotFoundException {
        List<Vprasanje> vprasanja = vprasanjeZrno.pridobiVprasanjeTAG(tagZrno.pridobiTag(tagID));
        List<OdgovorDTO> list = new ArrayList<>();

        for (Vprasanje v : vprasanja) {
            list.add(odgovorZrno.pridobiVrednost(v));
        }

        if (list.isEmpty()) {
            throw new NotFoundException();
        } else {
            return Response
                    .ok()
                    .entity(list)
                    .header("X-Total-Count", list.size())
                    .build();
        }
    }

    @Operation(
            description = "Return answer to a specific question.",
            summary = "Response",
            tags = "questions",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No questions found"
                    )})
    @Path("vprasanje/{vpr_id}/{value}")
    @POST
    public Response dodajOdgovor(
            @Parameter(description = "This is an ID of a question.", required = true)
            @PathParam("vpr_id") Integer vprID,
            @Parameter(description = "This is an answer.", required = true)
            @PathParam("value") Integer value) {

        if (value == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Please return an answer.").build();
        }
        Vprasanje trenutno = vprasanjeZrno.pridobiVprasanje(vprID);
        Double newVal = odgovorZrno.dodajOdgovor(trenutno, value);
        return Response
                .ok()
                .entity(newVal)
                .build();
    }

    @Operation(
            description = "Returns a list of available tags.",
            summary = "Response",
            tags = "tags",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "OK"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "No tags found"
                    )})
    @Path("tags")
    @GET
    public Response pridobiTage() {
        List<Tag> tags = tagZrno.pridobiVseTage();
        if (tags.isEmpty()) {
            throw new NotFoundException("No tags found");
        }
        return  Response
                .ok()
                .entity(tags)
                .build();
    }
}
