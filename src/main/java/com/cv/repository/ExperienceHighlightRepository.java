package com.cv.repository;

import com.cv.entity.ExperienceHighlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceHighlightRepository extends JpaRepository<ExperienceHighlight, Long> {
}
