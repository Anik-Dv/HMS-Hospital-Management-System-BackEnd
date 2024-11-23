package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Doctor Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
