package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "names")
public class Name {
    private Long id;
    private String name;
    private Long placeId;
    private String placeKind;
    private String lang;
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
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    @Column(name = "place_kind", nullable = false, length = -1)
    public String getPlaceKind() {
        return placeKind;
    }

    public void setPlaceKind(String placeKind) {
        this.placeKind = placeKind;
    }

    @Basic
    @Column(name = "lang", nullable = false, length = -1)
    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
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

        Name name1 = (Name) o;

        if (id != null ? !id.equals(name1.id) : name1.id != null) return false;
        if (name != null ? !name.equals(name1.name) : name1.name != null) return false;
        if (placeId != null ? !placeId.equals(name1.placeId) : name1.placeId != null) return false;
        if (placeKind != null ? !placeKind.equals(name1.placeKind) : name1.placeKind != null) return false;
        if (lang != null ? !lang.equals(name1.lang) : name1.lang != null) return false;
        if (createdAt != null ? !createdAt.equals(name1.createdAt) : name1.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(name1.updatedAt) : name1.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (placeKind != null ? placeKind.hashCode() : 0);
        result = 31 * result + (lang != null ? lang.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
