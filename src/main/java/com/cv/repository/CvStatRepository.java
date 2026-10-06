package com.cv.repository;

import com.cv.entity.CvStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CvStatRepository extends JpaRepository<CvStat, Long> {
    List<CvStat> findAllByOrderBySortOrderAsc();
}
