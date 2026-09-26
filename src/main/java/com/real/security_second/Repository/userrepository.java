package com.real.security_second.Repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface userrepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
}
