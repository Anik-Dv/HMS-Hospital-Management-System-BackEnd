package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Patient Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
