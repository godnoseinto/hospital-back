package mx.com.capacitacionhospital.hospital.external.jpa.model;

import javax.persistence.*;

import lombok.*;
import mx.com.capacitacionhospital.hospital.core.entity.Hospital;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "tho01_hospital")
public class HospitalJpa {

    @Id
    @SequenceGenerator(name = "tho01_hospital_id_hospital_seq", sequenceName = "tho01_hospital_id_hospital_seq", allocationSize = 1)
    @GeneratedValue(generator = "tho01_hospital_id_hospital_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_hospital")
    private Integer id;

    @Column(name = "tx_nombre")
    private String nombre;

    @Column(name = "st_activa")
    private boolean activo;

    public Hospital toEntity(){
        return Hospital.builder()
                .id(this.id)
                .nombre(this.nombre)
                .activo(this.activo)
                .build();
    }

    public static HospitalJpa fromEntity(Hospital hospital){
        return HospitalJpa.builder()
                .id(hospital.getId())
                .nombre(hospital.getNombre())
                .activo(hospital.getActivo())
                .build();
    }

}
