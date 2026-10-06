package com.cv.repository;

import com.cv.entity.SkillItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillItemRepository extends JpaRepository<SkillItem, Long> {
}
