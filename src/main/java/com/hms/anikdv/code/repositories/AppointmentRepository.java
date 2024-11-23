package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Appointment Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
