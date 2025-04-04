package com.sisterslab.bookerapp.repository;

import com.sisterslab.bookerapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String name);

    Optional<User> findByUsername(String username);
}
