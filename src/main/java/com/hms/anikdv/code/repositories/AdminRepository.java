package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Admin;
import com.hms.anikdv.code.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Admin Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
