package mx.com.capacitacionhospital.util.error.handler;

import javax.ws.rs.ext.*;
import javax.validation.*;
import javax.ws.rs.core.*;
import mx.com.capacitacionhospital.util.error.*;
import java.util.*;

@Provider
public class CustomValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<ErrorInfo> errores = e.getConstraintViolations().stream()
                .map(ErrorInfoGlobalMapper::constraintToError).collect(java.util.stream.Collectors.toList());
        return Response.status(Response.Status.BAD_REQUEST).entity(errores).build();
    }
}
