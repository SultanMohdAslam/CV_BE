package com.cv.dto;

import com.cv.entity.*;
import java.util.List;

public class CvDataDto {
    private PersonalInfo personal;
    private List<CvStat> stats;
    private List<Experience> experiences;
    private List<SkillCategory> skillCategories;
    private List<SystemShowcase> systemArchitectureShowcase;
    private List<Education> education;

    public CvDataDto() {
    }

    public CvDataDto(PersonalInfo personal,
                     List<CvStat> stats,
                     List<Experience> experiences,
                     List<SkillCategory> skillCategories,
                     List<SystemShowcase> systemArchitectureShowcase,
                     List<Education> education) {
        this.personal = personal;
        this.stats = stats;
        this.experiences = experiences;
        this.skillCategories = skillCategories;
        this.systemArchitectureShowcase = systemArchitectureShowcase;
        this.education = education;
    }

    public PersonalInfo getPersonal() {
        return personal;
    }

    public void setPersonal(PersonalInfo personal) {
        this.personal = personal;
    }

    public List<CvStat> getStats() {
        return stats;
    }

    public void setStats(List<CvStat> stats) {
        this.stats = stats;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public List<SkillCategory> getSkillCategories() {
        return skillCategories;
    }

    public void setSkillCategories(List<SkillCategory> skillCategories) {
        this.skillCategories = skillCategories;
    }

    public List<SystemShowcase> getSystemArchitectureShowcase() {
        return systemArchitectureShowcase;
    }

    public void setSystemArchitectureShowcase(List<SystemShowcase> systemArchitectureShowcase) {
        this.systemArchitectureShowcase = systemArchitectureShowcase;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }
}
