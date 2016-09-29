package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "assocs_assocs")
public class AssocAssoc {
    private Long id;
    private Long assoc1Id;
    private Long assoc2Id;
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
    @Column(name = "assoc1_id", nullable = false)
    public Long getAssoc1Id() {
        return assoc1Id;
    }

    public void setAssoc1Id(Long assoc1Id) {
        this.assoc1Id = assoc1Id;
    }

    @Basic
    @Column(name = "assoc2_id", nullable = false)
    public Long getAssoc2Id() {
        return assoc2Id;
    }

    public void setAssoc2Id(Long assoc2Id) {
        this.assoc2Id = assoc2Id;
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

        AssocAssoc that = (AssocAssoc) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (assoc1Id != null ? !assoc1Id.equals(that.assoc1Id) : that.assoc1Id != null) return false;
        if (assoc2Id != null ? !assoc2Id.equals(that.assoc2Id) : that.assoc2Id != null) return false;
        if (createdAt != null ? !createdAt.equals(that.createdAt) : that.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (assoc1Id != null ? assoc1Id.hashCode() : 0);
        result = 31 * result + (assoc2Id != null ? assoc2Id.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
