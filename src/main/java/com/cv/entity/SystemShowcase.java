package com.cv.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "system_showcases")
public class SystemShowcase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String company;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer sortOrder = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "showcase_tags", joinColumns = @JoinColumn(name = "showcase_id"))
    @Column(name = "tag_name")
    @OrderColumn(name = "tag_order")
    private List<String> tags = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "showcase_architecture_points", joinColumns = @JoinColumn(name = "showcase_id"))
    @Column(name = "point_text", columnDefinition = "TEXT")
    @OrderColumn(name = "point_order")
    private List<String> architecture = new ArrayList<>();

    public SystemShowcase() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getArchitecture() {
        return architecture;
    }

    public void setArchitecture(List<String> architecture) {
        this.architecture = architecture;
    }
}
