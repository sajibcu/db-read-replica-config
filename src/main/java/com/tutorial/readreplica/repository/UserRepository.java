package com.tutorial.readreplica.repository;

import com.tutorial.readreplica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User, Long > {
}
