package com.nrusev.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by nikolayrusev on 2/22/16.
 */
@Entity
@Table(name= "teams", uniqueConstraints=
    @UniqueConstraint(columnNames = {"code"}) )
@JsonIgnoreProperties(ignoreUnknown = true, value = "country")
@NamedEntityGraph(name = "team.country",
        attributeNodes = @NamedAttributeNode("country"))
public class Team extends AbstractAuditableEntity{

    private Long id;
    private String key;
    private String title;
    private String title2;
    private String code;
    private String synonyms;
    private Country country;
    private Long cityId;
    private Boolean club;
    private Long since;
    private String address;
    private String web;
    private Long assocId;
    private Boolean national;
    private Set<Event> events = new HashSet<>(0);

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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
    
    @ManyToMany
    @JoinTable(
    	      name="events_teams",
    	      joinColumns=@JoinColumn(name="team_id", referencedColumnName="id"),
    	      inverseJoinColumns=@JoinColumn(name="event_id", referencedColumnName="id"))
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
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
        if (country != null ? !country.equals(team.country) : team.country != null) return false;
        if (cityId != null ? !cityId.equals(team.cityId) : team.cityId != null) return false;
        if (club != null ? !club.equals(team.club) : team.club != null) return false;
        if (since != null ? !since.equals(team.since) : team.since != null) return false;
        if (address != null ? !address.equals(team.address) : team.address != null) return false;
        if (web != null ? !web.equals(team.web) : team.web != null) return false;
        if (assocId != null ? !assocId.equals(team.assocId) : team.assocId != null) return false;
        if (national != null ? !national.equals(team.national) : team.national != null) return false;

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
//        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (club != null ? club.hashCode() : 0);
        result = 31 * result + (since != null ? since.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (web != null ? web.hashCode() : 0);
        result = 31 * result + (assocId != null ? assocId.hashCode() : 0);
        result = 31 * result + (national != null ? national.hashCode() : 0);
        return result;
    }

}
