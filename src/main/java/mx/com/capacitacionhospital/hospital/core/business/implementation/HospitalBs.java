package mx.com.capacitacionhospital.hospital.core.business.implementation;

import io.vavr.control.Either;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import mx.com.capacitacionhospital.hospital.core.business.input.HospitalService;
import mx.com.capacitacionhospital.hospital.core.business.output.HospitalRepository;
import mx.com.capacitacionhospital.hospital.core.entity.Hospital;
import mx.com.capacitacionhospital.util.error.*;

@ApplicationScoped
public class HospitalBs implements HospitalService {

    @Inject
    HospitalRepository hospitalRepository;

    @Inject
    ErrorInfoGlobalMapper errorInfoGlobalMapper;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean create(Hospital hospital) {
        var hospitalSave = Hospital.builder()
                .nombre(hospital.getNombre())
                .activo(hospital.getActivo())
                .build();
        hospitalRepository.save(hospitalSave);
        return true;
    }

    @Override
    public Either<ErrorInfo, Hospital> get(Integer idHospital) {
        Either<ErrorInfo, Hospital> result = Either.left(errorInfoGlobalMapper.getRnS003());
         var hospital = hospitalRepository.findBy(idHospital);
         if(hospital.isPresent()){
             result = Either.right(hospital.get());
         }
        return result;
    }
}
