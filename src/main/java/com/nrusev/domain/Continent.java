package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "continents")
public class Continent {
    private Long id;
    private String name;
    private String slug;
    private String key;
    private Long placeId;
    private String altNames;
    private Date createdAt;
    private Date updatedAt;
    private Set<Country> countries;

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
    @Column(name = "slug", nullable = false, length = -1)
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
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
    @Column(name = "place_id", nullable = false)
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    @Basic
    @Column(name = "alt_names", nullable = true, length = -1)
    public String getAltNames() {
        return altNames;
    }

    public void setAltNames(String altNames) {
        this.altNames = altNames;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "continent")
    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Continent continent = (Continent) o;

        if (id != null ? !id.equals(continent.id) : continent.id != null) return false;
        if (name != null ? !name.equals(continent.name) : continent.name != null) return false;
        if (slug != null ? !slug.equals(continent.slug) : continent.slug != null) return false;
        if (key != null ? !key.equals(continent.key) : continent.key != null) return false;
        if (placeId != null ? !placeId.equals(continent.placeId) : continent.placeId != null) return false;
        if (altNames != null ? !altNames.equals(continent.altNames) : continent.altNames != null) return false;
        if (createdAt != null ? !createdAt.equals(continent.createdAt) : continent.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(continent.updatedAt) : continent.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (altNames != null ? altNames.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
