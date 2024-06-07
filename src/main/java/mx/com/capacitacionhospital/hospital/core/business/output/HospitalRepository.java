package mx.com.capacitacionhospital.hospital.core.business.output;

import mx.com.capacitacionhospital.hospital.core.entity.Hospital;
import java.util.Optional;
import java.util.Set;

public interface HospitalRepository {

    Optional<Hospital> save(Hospital hospital);
    Optional<Hospital> findBy(Integer idHospital);

}
