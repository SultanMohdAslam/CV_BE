package com.cv.repository;

import com.cv.entity.AdminSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminSecurityRepository extends JpaRepository<AdminSecurity, Long> {
    Optional<AdminSecurity> findFirstByOrderByIdAsc();
}
