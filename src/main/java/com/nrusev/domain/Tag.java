package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "tags")
public class Tag {
    private Long id;
    private String key;
    private String slug;
    private String name;
    private Long grade;
    private Long parentId;
    private Date createdAt;
    private Date updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "key", nullable = false, length = -1)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "slug", nullable = false, length = -1)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Basic
    @Column(name = "name", nullable = true, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "grade", nullable = false)
    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "parent_id", nullable = true)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tag tag = (Tag) o;

        if (id != null ? !id.equals(tag.id) : tag.id != null) return false;
        if (key != null ? !key.equals(tag.key) : tag.key != null) return false;
        if (slug != null ? !slug.equals(tag.slug) : tag.slug != null) return false;
        if (name != null ? !name.equals(tag.name) : tag.name != null) return false;
        if (grade != null ? !grade.equals(tag.grade) : tag.grade != null) return false;
        if (parentId != null ? !parentId.equals(tag.parentId) : tag.parentId != null) return false;
        if (createdAt != null ? !createdAt.equals(tag.createdAt) : tag.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(tag.updatedAt) : tag.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
