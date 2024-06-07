package mx.com.capacitacionhospital.hospital.core.business.input;

import mx.com.capacitacionhospital.hospital.core.entity.Hospital;
import io.vavr.control.*;
import mx.com.capacitacionhospital.util.error.*;

public interface HospitalService {
    Boolean create(Hospital hospital);
    Either<ErrorInfo, Hospital> get(Integer idHospital);
}
