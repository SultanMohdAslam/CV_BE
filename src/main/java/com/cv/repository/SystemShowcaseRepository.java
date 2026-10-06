package com.cv.repository;

import com.cv.entity.SystemShowcase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SystemShowcaseRepository extends JpaRepository<SystemShowcase, Long> {
    List<SystemShowcase> findAllByOrderBySortOrderAsc();
}
