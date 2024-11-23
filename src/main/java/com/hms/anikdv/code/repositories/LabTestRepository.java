package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.LabTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * LabTestRepository Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface LabTestRepository extends JpaRepository<LabTest,Long> {
    List<LabTest> findByPatientPatientId(Long patientId);
}
