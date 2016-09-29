package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "taggings")
public class Tagging {
    private Long id;
    private Long tagId;
    private Long taggableId;
    private String taggableType;
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
    @Column(name = "tag_id", nullable = false)
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Basic
    @Column(name = "taggable_id", nullable = false)
    public Long getTaggableId() {
        return taggableId;
    }

    public void setTaggableId(Long taggableId) {
        this.taggableId = taggableId;
    }

    @Basic
    @Column(name = "taggable_type", nullable = false, length = -1)
    public String getTaggableType() {
        return taggableType;
    }

    public void setTaggableType(String taggableType) {
        this.taggableType = taggableType;
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

        Tagging tagging = (Tagging) o;

        if (id != null ? !id.equals(tagging.id) : tagging.id != null) return false;
        if (tagId != null ? !tagId.equals(tagging.tagId) : tagging.tagId != null) return false;
        if (taggableId != null ? !taggableId.equals(tagging.taggableId) : tagging.taggableId != null) return false;
        if (taggableType != null ? !taggableType.equals(tagging.taggableType) : tagging.taggableType != null)
            return false;
        if (createdAt != null ? !createdAt.equals(tagging.createdAt) : tagging.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(tagging.updatedAt) : tagging.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        result = 31 * result + (taggableId != null ? taggableId.hashCode() : 0);
        result = 31 * result + (taggableType != null ? taggableType.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
