package com.nrusev.domain;

import javax.persistence.*;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "country_codes")
public class CountryCode {
    private Long id;
    private String name;
    private String kind;
    private Long countryId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "kind", nullable = false, length = -1)
    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Basic
    @Column(name = "country_id", nullable = false)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountryCode that = (CountryCode) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null) return false;
        if (countryId != null ? !countryId.equals(that.countryId) : that.countryId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        return result;
    }
}
