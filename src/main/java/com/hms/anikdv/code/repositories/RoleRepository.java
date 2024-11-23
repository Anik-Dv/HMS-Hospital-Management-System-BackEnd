package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Role Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findById(Long name);
    Optional<Role> findByName(String name);
}
