package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "assocs")
public class Assoc {
    private Long id;
    private String key;
    private String title;
    private Long since;
    private String web;
    private Long countryId;
    private Boolean national;
    private Boolean continental;
    private Boolean intercontinental;
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
    @Column(name = "title", nullable = false, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "since", nullable = true)
    public Long getSince() {
        return since;
    }

    public void setSince(Long since) {
        this.since = since;
    }

    @Basic
    @Column(name = "web", nullable = true, length = -1)
    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @Basic
    @Column(name = "country_id", nullable = true)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "national", nullable = false)
    public Boolean getNational() {
        return national;
    }

    public void setNational(Boolean national) {
        this.national = national;
    }

    @Basic
    @Column(name = "continental", nullable = false)
    public Boolean getContinental() {
        return continental;
    }

    public void setContinental(Boolean continental) {
        this.continental = continental;
    }

    @Basic
    @Column(name = "intercontinental", nullable = false)
    public Boolean getIntercontinental() {
        return intercontinental;
    }

    public void setIntercontinental(Boolean intercontinental) {
        this.intercontinental = intercontinental;
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

        Assoc assoc = (Assoc) o;

        if (id != null ? !id.equals(assoc.id) : assoc.id != null) return false;
        if (key != null ? !key.equals(assoc.key) : assoc.key != null) return false;
        if (title != null ? !title.equals(assoc.title) : assoc.title != null) return false;
        if (since != null ? !since.equals(assoc.since) : assoc.since != null) return false;
        if (web != null ? !web.equals(assoc.web) : assoc.web != null) return false;
        if (countryId != null ? !countryId.equals(assoc.countryId) : assoc.countryId != null) return false;
        if (national != null ? !national.equals(assoc.national) : assoc.national != null) return false;
        if (continental != null ? !continental.equals(assoc.continental) : assoc.continental != null) return false;
        if (intercontinental != null ? !intercontinental.equals(assoc.intercontinental) : assoc.intercontinental != null)
            return false;
        if (createdAt != null ? !createdAt.equals(assoc.createdAt) : assoc.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(assoc.updatedAt) : assoc.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (national != null ? national.hashCode() : 0);
        result = 31 * result + (continental != null ? continental.hashCode() : 0);
        result = 31 * result + (intercontinental != null ? intercontinental.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
