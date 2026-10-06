package com.cv.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skill_categories")
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String category;

    private String color = "emerald";
    private Integer sortOrder = 0;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("sortOrder ASC")
    private List<SkillItem> skills = new ArrayList<>();

    public SkillCategory() {
    }

    public SkillCategory(String category, String color, Integer sortOrder) {
        this.category = category;
        this.color = color;
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<SkillItem> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillItem> skills) {
        this.skills = skills;
        if (skills != null) {
            for (SkillItem s : skills) {
                s.setCategory(this);
            }
        }
    }

    public void addSkill(SkillItem item) {
        skills.add(item);
        item.setCategory(this);
    }
}
