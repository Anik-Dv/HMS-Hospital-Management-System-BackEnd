package com.hms.anikdv.code.repositories;

import com.hms.anikdv.code.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User Repository
 * @author AnikDv
 * @catagory repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param name
     * @return resource form DB
     */
    Optional<User> findByName(String name);
}
