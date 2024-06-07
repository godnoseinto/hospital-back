package mx.com.capacitacionhospital.hospital.external.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mx.com.capacitacionhospital.hospital.core.entity.Hospital;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Size;


@Builder
@Getter
@Schema(name = "Hospital", description = "Entidad con la información de un hospital")
public class HospitalDto {


    @JsonProperty
    @Schema(description = "Identificador del hospital")
    private Integer id;
    @JsonProperty
    @Schema(description = "Nombre del hospital")
    @Size( message = "La longitud no es valida", max = 200)
    private String nombre;
    @JsonProperty
    @Schema(description = "bandera que indica si el hospital esta activo")
    private Boolean activo;

    public Hospital toEntity(){
        return Hospital.builder()
                .id(this.id)
                .nombre(this.nombre)
                .activo(this.activo)
                .build();
    }

    public static HospitalDto fromEntity(Hospital hospital){
        return HospitalDto.builder()
                .id(hospital.getId())
                .nombre(hospital.getNombre())
                .activo(hospital.getActivo())
                .build();
    }

}
