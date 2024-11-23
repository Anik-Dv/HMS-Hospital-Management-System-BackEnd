package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * PharmacyRepository Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {
    List<Pharmacy> findByExpirationDateBefore(LocalDate date); // To find expired drugs
}
