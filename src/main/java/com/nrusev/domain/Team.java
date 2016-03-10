package com.nrusev.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Entity
@Table(name= "teams", uniqueConstraints=
    @UniqueConstraint(columnNames = {"code"}) )
@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {

    private Long id;
    private String key;
    private String title;
    private String title2;
    private String code;
    private String synonyms;
    private Long countryId;
    private Long cityId;
    private Boolean club;
    private Long since;
    private String address;
    private String web;
    private Long assocId;
    private Boolean national;
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
    @Column(name = "title2", nullable = true, length = -1)
    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
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
    @Column(name = "synonyms", nullable = true, length = -1)
    public String getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(String synonyms) {
        this.synonyms = synonyms;
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
    @Column(name = "city_id", nullable = true)
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Basic
    @Column(name = "club", nullable = false)
    public Boolean getClub() {
        return club;
    }

    public void setClub(Boolean club) {
        this.club = club;
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
    @Column(name = "address", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    @Column(name = "assoc_id", nullable = true)
    public Long getAssocId() {
        return assocId;
    }

    public void setAssocId(Long assocId) {
        this.assocId = assocId;
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

        Team team = (Team) o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (key != null ? !key.equals(team.key) : team.key != null) return false;
        if (title != null ? !title.equals(team.title) : team.title != null) return false;
        if (title2 != null ? !title2.equals(team.title2) : team.title2 != null) return false;
        if (code != null ? !code.equals(team.code) : team.code != null) return false;
        if (synonyms != null ? !synonyms.equals(team.synonyms) : team.synonyms != null) return false;
        if (countryId != null ? !countryId.equals(team.countryId) : team.countryId != null) return false;
        if (cityId != null ? !cityId.equals(team.cityId) : team.cityId != null) return false;
        if (club != null ? !club.equals(team.club) : team.club != null) return false;
        if (since != null ? !since.equals(team.since) : team.since != null) return false;
        if (address != null ? !address.equals(team.address) : team.address != null) return false;
        if (web != null ? !web.equals(team.web) : team.web != null) return false;
        if (assocId != null ? !assocId.equals(team.assocId) : team.assocId != null) return false;
        if (national != null ? !national.equals(team.national) : team.national != null) return false;
        if (createdAt != null ? !createdAt.equals(team.createdAt) : team.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(team.updatedAt) : team.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (title2 != null ? title2.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (synonyms != null ? synonyms.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (club != null ? club.hashCode() : 0);
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (assocId != null ? assocId.hashCode() : 0);
        result = 31 * result + (national != null ? national.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
