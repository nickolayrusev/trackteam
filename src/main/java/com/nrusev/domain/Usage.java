package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "usages")
public class Usage {
    private Long id;
    private Long countryId;
    private Long langId;
    private Boolean official;
    private Boolean minor;
    private Double percent;
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
    @Column(name = "country_id", nullable = false)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "lang_id", nullable = false)
    public Long getLangId() {
        return langId;
    }

    public void setLangId(Long langId) {
        this.langId = langId;
    }

    @Basic
    @Column(name = "official", nullable = false)
    public Boolean getOfficial() {
        return official;
    }

    public void setOfficial(Boolean official) {
        this.official = official;
    }

    @Basic
    @Column(name = "minor", nullable = false)
    public Boolean getMinor() {
        return minor;
    }

    public void setMinor(Boolean minor) {
        this.minor = minor;
    }

    @Basic
    @Column(name = "percent", nullable = true, precision = 0)
    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
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

        Usage usage = (Usage) o;

        if (id != null ? !id.equals(usage.id) : usage.id != null) return false;
        if (countryId != null ? !countryId.equals(usage.countryId) : usage.countryId != null) return false;
        if (langId != null ? !langId.equals(usage.langId) : usage.langId != null) return false;
        if (official != null ? !official.equals(usage.official) : usage.official != null) return false;
        if (minor != null ? !minor.equals(usage.minor) : usage.minor != null) return false;
        if (percent != null ? !percent.equals(usage.percent) : usage.percent != null) return false;
        if (createdAt != null ? !createdAt.equals(usage.createdAt) : usage.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(usage.updatedAt) : usage.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (langId != null ? langId.hashCode() : 0);
        result = 31 * result + (official != null ? official.hashCode() : 0);
        result = 31 * result + (minor != null ? minor.hashCode() : 0);
        result = 31 * result + (percent != null ? percent.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
