package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MedicationRepository Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByPatientPatientId(Long patientId);
    List<Medication> findByDoctorDoctorId(Long doctorId);
}
