package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "parts")
public class Part {
    private Long id;
    private String name;
    private String key;
    private Long placeId;
    private String code;
    private String abbr;
    private String iso;
    private String nuts;
    private String altNames;
    private Long stateId;
    private Long level;
    private Long pop;
    private Long area;
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
    @Column(name = "code", nullable = true, length = -1)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "abbr", nullable = true, length = -1)
    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Basic
    @Column(name = "iso", nullable = true, length = -1)
    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    @Basic
    @Column(name = "nuts", nullable = true, length = -1)
    public String getNuts() {
        return nuts;
    }

    public void setNuts(String nuts) {
        this.nuts = nuts;
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
    @Column(name = "state_id", nullable = false)
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @Basic
    @Column(name = "pop", nullable = true)
    public Long getPop() {
        return pop;
    }

    public void setPop(Long pop) {
        this.pop = pop;
    }

    @Basic
    @Column(name = "area", nullable = true)
    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
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

        Part part = (Part) o;

        if (id != null ? !id.equals(part.id) : part.id != null) return false;
        if (name != null ? !name.equals(part.name) : part.name != null) return false;
        if (key != null ? !key.equals(part.key) : part.key != null) return false;
        if (placeId != null ? !placeId.equals(part.placeId) : part.placeId != null) return false;
        if (code != null ? !code.equals(part.code) : part.code != null) return false;
        if (abbr != null ? !abbr.equals(part.abbr) : part.abbr != null) return false;
        if (iso != null ? !iso.equals(part.iso) : part.iso != null) return false;
        if (nuts != null ? !nuts.equals(part.nuts) : part.nuts != null) return false;
        if (altNames != null ? !altNames.equals(part.altNames) : part.altNames != null) return false;
        if (stateId != null ? !stateId.equals(part.stateId) : part.stateId != null) return false;
        if (level != null ? !level.equals(part.level) : part.level != null) return false;
        if (pop != null ? !pop.equals(part.pop) : part.pop != null) return false;
        if (area != null ? !area.equals(part.area) : part.area != null) return false;
        if (createdAt != null ? !createdAt.equals(part.createdAt) : part.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(part.updatedAt) : part.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (abbr != null ? abbr.hashCode() : 0);
        result = 31 * result + (iso != null ? iso.hashCode() : 0);
        result = 31 * result + (nuts != null ? nuts.hashCode() : 0);
        result = 31 * result + (altNames != null ? altNames.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (pop != null ? pop.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
