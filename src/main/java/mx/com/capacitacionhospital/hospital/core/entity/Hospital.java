package mx.com.capacitacionhospital.hospital.core.entity;

import lombok.*;

@Builder
@Getter
@AllArgsConstructor
public class Hospital {

    private Integer id;
    private String nombre;
    private Boolean activo;
}
