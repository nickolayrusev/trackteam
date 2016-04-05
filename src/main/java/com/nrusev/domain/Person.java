package com.nrusev.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "persons")
public class Person {
    private Long id;
    private String key;
    private String name;
    private String synonyms;
    private String code;
    private Date bornAt;
    private Long cityId;
    private Long stateId;
    private Long countryId;
    private Long nationalityId;
    private java.util.Date createdAt;
    private java.util.Date updatedAt;

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
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "synonyms", nullable = true, length = -1)
    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
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
    @Column(name = "born_at", nullable = true)
    public Date getBornAt() {
        return bornAt;
    }

    public void setBornAt(Date bornAt) {
        this.bornAt = bornAt;
    }

    @Basic
    @Column(name = "city_id", nullable = true)
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
    @Column(name = "country_id", nullable = true)
    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "nationality_id", nullable = true)
    public Long getNationalityId() {
        return nationalityId;
    }

    public void setNationalityId(Long nationalityId) {
        this.nationalityId = nationalityId;
    }

    @Basic
    @Column(name = "created_at", nullable = true)
    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = true)
    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != null ? !id.equals(person.id) : person.id != null) return false;
        if (key != null ? !key.equals(person.key) : person.key != null) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (synonyms != null ? !synonyms.equals(person.synonyms) : person.synonyms != null) return false;
        if (code != null ? !code.equals(person.code) : person.code != null) return false;
        if (bornAt != null ? !bornAt.equals(person.bornAt) : person.bornAt != null) return false;
        if (cityId != null ? !cityId.equals(person.cityId) : person.cityId != null) return false;
        if (stateId != null ? !stateId.equals(person.stateId) : person.stateId != null) return false;
        if (countryId != null ? !countryId.equals(person.countryId) : person.countryId != null) return false;
        if (nationalityId != null ? !nationalityId.equals(person.nationalityId) : person.nationalityId != null)
            return false;
        if (createdAt != null ? !createdAt.equals(person.createdAt) : person.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(person.updatedAt) : person.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (synonyms != null ? synonyms.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (bornAt != null ? bornAt.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (stateId != null ? stateId.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (nationalityId != null ? nationalityId.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
