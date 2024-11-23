package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InsuranceRepository Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByPatientPatientId(Long patientId);
}
