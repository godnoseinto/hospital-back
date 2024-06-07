package mx.com.capacitacionhospital.util.error.handler;

import javax.ws.rs.ext.*;
import javax.ws.rs.core.*;
import javax.persistence.*;
import mx.com.capacitacionhospital.util.error.*;

@Provider
public class CustomPersistenceExceptionMapper implements ExceptionMapper<PersistenceException> {
    @Override
    public Response toResponse(PersistenceException e) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        ErrorInfo error =  ErrorInfo.builder()
                .code("CAPA_PERSISTENCIA")
                .type(mx.com.capacitacionhospital.util.enums.ErrorType.REQUEST)
                .message("Error en la capa de la persistencia")
                .ruta(elements[2].getClass().toString())
                .build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
