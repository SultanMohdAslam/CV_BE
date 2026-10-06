package com.cv.repository;

import com.cv.entity.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    List<SkillCategory> findAllByOrderBySortOrderAsc();
}
