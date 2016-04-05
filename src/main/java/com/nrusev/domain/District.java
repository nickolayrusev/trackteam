package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "districts")
public class District {
    private Long id;
    private String name;
    private String key;
    private Long placeId;
    private String code;
    private String altNames;
    private Long cityId;
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
    @Column(name = "city_id", nullable = false)
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

        District district = (District) o;

        if (id != null ? !id.equals(district.id) : district.id != null) return false;
        if (name != null ? !name.equals(district.name) : district.name != null) return false;
        if (key != null ? !key.equals(district.key) : district.key != null) return false;
        if (placeId != null ? !placeId.equals(district.placeId) : district.placeId != null) return false;
        if (code != null ? !code.equals(district.code) : district.code != null) return false;
        if (altNames != null ? !altNames.equals(district.altNames) : district.altNames != null) return false;
        if (cityId != null ? !cityId.equals(district.cityId) : district.cityId != null) return false;
        if (pop != null ? !pop.equals(district.pop) : district.pop != null) return false;
        if (area != null ? !area.equals(district.area) : district.area != null) return false;
        if (createdAt != null ? !createdAt.equals(district.createdAt) : district.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(district.updatedAt) : district.updatedAt != null) return false;

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
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (pop != null ? pop.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
