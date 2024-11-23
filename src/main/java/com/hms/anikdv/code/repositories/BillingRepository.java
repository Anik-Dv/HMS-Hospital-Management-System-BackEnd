package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Billing Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface BillingRepository extends JpaRepository<Billing,Long> {
}
