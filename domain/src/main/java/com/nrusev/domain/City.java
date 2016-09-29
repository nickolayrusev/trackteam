package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "cities")
public class City {
    private Long id;
    private String name;
    private String key;
    private Long placeId;
    private String code;
    private String altNames;
    private Long countryId;
    private Long stateId;
    private Long partId;
    private Long countyId;
    private Long muniId;
    private Long metroId;
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
    @Column(name = "alt_names", nullable = true, length = -1)
    public String getAltNames() {
        return altNames;
    }

    public void setAltNames(String altNames) {
        this.altNames = altNames;
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
    @Column(name = "state_id", nullable = true)
    public Long getStateId() {
        return stateId;
    }

    public void setStateId(Long stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "part_id", nullable = true)
    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
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
    @Column(name = "muni_id", nullable = true)
    public Long getMuniId() {
        return muniId;
    }

    public void setMuniId(Long muniId) {
        this.muniId = muniId;
    }

    @Basic
    @Column(name = "metro_id", nullable = true)
    public Long getMetroId() {
        return metroId;
    }

    public void setMetroId(Long metroId) {
        this.metroId = metroId;
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

        City city = (City) o;

        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (key != null ? !key.equals(city.key) : city.key != null) return false;
        if (placeId != null ? !placeId.equals(city.placeId) : city.placeId != null) return false;
        if (code != null ? !code.equals(city.code) : city.code != null) return false;
        if (altNames != null ? !altNames.equals(city.altNames) : city.altNames != null) return false;
        if (countryId != null ? !countryId.equals(city.countryId) : city.countryId != null) return false;
        if (stateId != null ? !stateId.equals(city.stateId) : city.stateId != null) return false;
        if (partId != null ? !partId.equals(city.partId) : city.partId != null) return false;
        if (countyId != null ? !countyId.equals(city.countyId) : city.countyId != null) return false;
        if (muniId != null ? !muniId.equals(city.muniId) : city.muniId != null) return false;
        if (metroId != null ? !metroId.equals(city.metroId) : city.metroId != null) return false;
        if (pop != null ? !pop.equals(city.pop) : city.pop != null) return false;
        if (area != null ? !area.equals(city.area) : city.area != null) return false;
        if (createdAt != null ? !createdAt.equals(city.createdAt) : city.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(city.updatedAt) : city.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (altNames != null ? altNames.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (partId != null ? partId.hashCode() : 0);
        result = 31 * result + (countyId != null ? countyId.hashCode() : 0);
        result = 31 * result + (muniId != null ? muniId.hashCode() : 0);
        result = 31 * result + (metroId != null ? metroId.hashCode() : 0);
        result = 31 * result + (pop != null ? pop.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
