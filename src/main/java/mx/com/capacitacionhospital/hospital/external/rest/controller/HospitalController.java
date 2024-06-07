package mx.com.capacitacionhospital.hospital.external.rest.controller;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.com.capacitacionhospital.hospital.core.business.input.HospitalService;
import mx.com.capacitacionhospital.hospital.external.rest.dto.HospitalDto;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import mx.com.capacitacionhospital.util.error.*;

@Path("/hospital")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Hospital")
public class HospitalController {

    @Inject
    HospitalService hospitalService;

    @GET
    @Path("/{idHospital}")
    @APIResponse(responseCode = "200", description = "Petición exitosa", content = @Content(schema = @Schema(implementation = HospitalDto.class)))
    @APIResponse(responseCode = "400", description = "Error en la peticion", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
    @APIResponse(responseCode = "404", description = "Error en la peticion: entidad no encontradas", content = @Content(schema = @Schema(implementation = ErrorInfo.class)))
    public Response get(@PathParam("idHospital") Integer idHospital) {
        return hospitalService.get(idHospital).map( hospital ->
                        Response.ok(HospitalDto.fromEntity(hospital)))
                .getOrElseGet(ErrorInfoGlobalMapper::errorCodeToResponseBuilder).build();
    }

    @POST
    @APIResponse(responseCode = "200", description = "Petición exitosa", content = @Content(schema = @Schema(implementation = Boolean.class)))
    public Response create(@Valid HospitalDto hospitalDto) {
        return Response.ok(hospitalService.create(hospitalDto.toEntity())).build();
    }

}
