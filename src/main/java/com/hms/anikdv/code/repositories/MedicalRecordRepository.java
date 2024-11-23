package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MedicalRecord Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {
}
