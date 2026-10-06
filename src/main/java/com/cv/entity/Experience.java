package com.cv.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "experiences")
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expSlug; // e.g., "foodi", "adn-diginet"
    private String role;
    private String company;
    private String period;
    private Boolean isCurrent = false;
    private String department;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private Integer sortOrder = 0;

    @OneToMany(mappedBy = "experience", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    private List<ExperienceHighlight> highlights = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "experience_tech_stack", joinColumns = @JoinColumn(name = "experience_id"))
    @Column(name = "tech_name")
    @OrderColumn(name = "tech_order")
    private List<String> techStack = new ArrayList<>();

    public Experience() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpSlug() {
        return expSlug;
    }

    public void setExpSlug(String expSlug) {
        this.expSlug = expSlug;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Boolean getIsCurrent() {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent) {
        this.isCurrent = isCurrent;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<ExperienceHighlight> getHighlights() {
        return highlights;
    }

    public void setHighlights(List<ExperienceHighlight> highlights) {
        this.highlights = highlights;
        if (highlights != null) {
            for (ExperienceHighlight h : highlights) {
                h.setExperience(this);
            }
        }
    }

    public void addHighlight(ExperienceHighlight highlight) {
        highlights.add(highlight);
        highlight.setExperience(this);
    }

    public List<String> getTechStack() {
        return techStack;
    }

    public void setTechStack(List<String> techStack) {
        this.techStack = techStack;
    }
}
