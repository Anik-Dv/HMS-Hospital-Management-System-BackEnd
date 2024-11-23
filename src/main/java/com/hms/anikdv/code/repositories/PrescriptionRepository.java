package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Prescription Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {
}
