package mx.com.capacitacionhospital.util.error;

import lombok.*;
import mx.com.capacitacionhospital.util.enums.*;
import io.quarkus.runtime.annotations.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class ErrorInfo {
    private String code;
    private ErrorType type;
    private String message;
    private String ruta;
}
