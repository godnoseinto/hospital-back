package mx.com.capacitacionhospital.hospital.external.jpa.dao;

import io.quarkus.hibernate.orm.PersistenceUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import mx.com.capacitacionhospital.hospital.core.business.output.HospitalRepository;
import mx.com.capacitacionhospital.hospital.core.entity.Hospital;
import mx.com.capacitacionhospital.hospital.external.jpa.model.HospitalJpa;
import mx.com.capacitacionhospital.hospital.external.jpa.repository.HospitalJpaRepository;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class HospitalDao implements HospitalRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    HospitalJpaRepository hospitalJpaRepository;

    @Inject
    @PersistenceUnit("reading")
    EntityManager entityManagerReading;

    private static final String QUERY_GET_HOSPITAL_BY_ID = "select * from tho01_hospital where id_hospital =:idHospital";


    private static final String PARAM_ID_HOSPITAL = "idHospital";

    @Override
    public Optional<Hospital> save(Hospital hospital) {
        return Optional.of(hospitalJpaRepository
                .save(HospitalJpa.
                        fromEntity(hospital)).toEntity());
    }

    @Override
    @SuppressWarnings("unchecked")
    public Optional<Hospital> findBy(Integer idHospital) {
        Stream<HospitalJpa> stream = entityManagerReading.createNativeQuery(QUERY_GET_HOSPITAL_BY_ID, HospitalJpa.class)
                .setParameter(PARAM_ID_HOSPITAL, idHospital)
                .getResultStream();
        return stream.map(HospitalJpa::toEntity).findFirst();
    }
}
