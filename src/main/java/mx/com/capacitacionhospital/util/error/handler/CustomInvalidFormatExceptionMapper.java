package mx.com.capacitacionhospital.util.error.handler;

import com.fasterxml.jackson.databind.exc.*;
import javax.ws.rs.ext.*;
import javax.ws.rs.core.*;
import mx.com.capacitacionhospital.util.enums.*;
import mx.com.capacitacionhospital.util.error.*;

@Provider
public class CustomInvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {
    @Override
    public Response toResponse(InvalidFormatException e) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        ErrorInfo error =  ErrorInfo.builder()
                .code("CAPA_VALIDACION")
                .type(ErrorType.FIELD)
                .message("Error en los formatos utilizados en los atributos json")
                .ruta(elements[0].getClass().toString())
                .build();
        return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
    }
}
