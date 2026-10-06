package com.cv.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cv_stats")
public class CvStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String value;
    private String detail;
    private Integer sortOrder = 0;

    public CvStat() {
    }

    public CvStat(String label, String value, String detail, Integer sortOrder) {
        this.label = label;
        this.value = value;
        this.detail = detail;
        this.sortOrder = sortOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
