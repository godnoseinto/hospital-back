package mx.com.capacitacionhospital.util.error;

import com.fasterxml.jackson.annotation.*;
import org.eclipse.microprofile.openapi.annotations.media.*;
import java.util.*;

import io.quarkus.runtime.annotations.*;
import lombok.*;

@Getter
@NoArgsConstructor
@RegisterForReflection
public class ErrorInfoData {

    @JsonProperty
    @Schema(description = "Codigo asociado con el error")
   private List<ErrorInfo> erroresInformacion;

    public void setErroresInformacion(java.util.List<ErrorInfo> erroresInformacion) {
        this.erroresInformacion = erroresInformacion;
    }
}
