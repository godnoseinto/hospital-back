package mx.com.capacitacionhospital.util.error;

import java.util.*;
import javax.validation.*;
import lombok.extern.slf4j.*;
import lombok.*;
import com.fasterxml.jackson.databind.*;
import java.io.*;
import  io.quarkus.runtime.*;
import javax.inject.*;
import javax.ws.rs.core.*;
import mx.com.capacitacionhospital.util.enums.ErrorType;

@Slf4j
@Startup
@Singleton
@Getter
public class ErrorInfoGlobalMapper {

    protected ErrorInfoData errorInfo;

    private final ErrorInfo rnS001;
    private final ErrorInfo rnS002;
    private final ErrorInfo rnS003;
    public ErrorInfoGlobalMapper(){
        errorInfo = load("global-errorinfo.json").orElseThrow();
        rnS001 = errorInfo.getErroresInformacion().get(0);
        rnS002 = errorInfo.getErroresInformacion().get(1);
        rnS003 = errorInfo.getErroresInformacion().get(2);
    }

    public static <T> ErrorInfo constraintToError(ConstraintViolation<T> ve) {
        return ErrorInfo.builder()
                .type(ErrorType.FIELD)
                .code(ve.getMessage())
                .message(ve.getMessage())
                .ruta(ve.getPropertyPath().toString()).build();
    }

    public static Response.ResponseBuilder errorCodeToResponseBuilder(ErrorInfo errorInfo) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        errorInfo.setType(ErrorType.REQUEST);
        if(elements.length > 0) {
            String clase = elements[3].getClassName();
            errorInfo.setRuta(clase);
        }
        var respuesta = Response.status(Response.Status.BAD_REQUEST).entity(errorInfo);
        if (Response.Status.NOT_FOUND.name().equals(errorInfo.getCode())) {
            respuesta = Response.status(Response.Status.NOT_FOUND).entity(errorInfo);
        }
        return respuesta;
    }

    public Optional<ErrorInfoData> load(String file) {
        ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        InputStream input = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(file);
        try {
            log.info("Loading {}", file);
            ErrorInfoData errorInfoDto = mapper.readValue(input, ErrorInfoData.class);
            return Optional.of(errorInfoDto);
        } catch (Exception e) {
            log.error("Cannot read {}", file, e);
        } finally {
            try {
                if (input != null) {
                    input.close();
                }
            } catch (java.io.IOException e) {
                log.error("Cannot close {}", file, e);
            }
        }
        return java.util.Optional.empty();
    }
}
