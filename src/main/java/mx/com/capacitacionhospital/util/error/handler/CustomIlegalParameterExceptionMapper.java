package mx.com.capacitacionhospital.util.error.handler;

import javax.ws.rs.ext.*;
import javax.ws.rs.core.*;
import mx.com.capacitacionhospital.util.error.*;
import mx.com.capacitacionhospital.util.enums.*;

@Provider
public class CustomIlegalParameterExceptionMapper implements ExceptionMapper<IllegalArgumentException> {
    @Override
    public javax.ws.rs.core.Response toResponse(IllegalArgumentException e) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        ErrorInfo error =  ErrorInfo.builder()
                .code("CAPA_PERSISTENCIA")
                .type(ErrorType.REQUEST)
                .message("Error en los parametros utilizados")
                .ruta(elements[2].getClass().toString())
                .build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
    }
}
