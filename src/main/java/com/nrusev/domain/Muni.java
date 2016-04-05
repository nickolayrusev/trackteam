package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "munis")
public class Muni {
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
    private Long countyId;
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
    @Column(name = "county_id", nullable = true)
    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
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

        Muni muni = (Muni) o;

        if (id != null ? !id.equals(muni.id) : muni.id != null) return false;
        if (name != null ? !name.equals(muni.name) : muni.name != null) return false;
        if (key != null ? !key.equals(muni.key) : muni.key != null) return false;
        if (placeId != null ? !placeId.equals(muni.placeId) : muni.placeId != null) return false;
        if (code != null ? !code.equals(muni.code) : muni.code != null) return false;
        if (abbr != null ? !abbr.equals(muni.abbr) : muni.abbr != null) return false;
        if (iso != null ? !iso.equals(muni.iso) : muni.iso != null) return false;
        if (nuts != null ? !nuts.equals(muni.nuts) : muni.nuts != null) return false;
        if (altNames != null ? !altNames.equals(muni.altNames) : muni.altNames != null) return false;
        if (stateId != null ? !stateId.equals(muni.stateId) : muni.stateId != null) return false;
        if (countyId != null ? !countyId.equals(muni.countyId) : muni.countyId != null) return false;
        if (level != null ? !level.equals(muni.level) : muni.level != null) return false;
        if (pop != null ? !pop.equals(muni.pop) : muni.pop != null) return false;
        if (area != null ? !area.equals(muni.area) : muni.area != null) return false;
        if (createdAt != null ? !createdAt.equals(muni.createdAt) : muni.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(muni.updatedAt) : muni.updatedAt != null) return false;

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
        result = 31 * result + (countyId != null ? countyId.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (pop != null ? pop.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
