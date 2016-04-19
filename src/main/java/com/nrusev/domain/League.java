package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "leagues")
public class League {
    private Long id;
    private String key;
    private String title;
//    private Long countryId;
    private Country country;
    private Boolean club;
    private Date createdAt;
    private Date updatedAt;
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

//    @Basic
//    @Column(name = "country_id", nullable = true)
//    public Long getCountryId() {
//        return countryId;
//    }
//
//    public void setCountryId(Long countryId) {
//        this.countryId = countryId;
//    }

    @Basic
    @Column(name = "club", nullable = false)
    public Boolean getClub() {
        return club;
    }

    public void setClub(Boolean club) {
        this.club = club;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "league")
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        League league = (League) o;

        if (id != null ? !id.equals(league.id) : league.id != null) return false;
        if (key != null ? !key.equals(league.key) : league.key != null) return false;
        if (title != null ? !title.equals(league.title) : league.title != null) return false;
//        if (countryId != null ? !countryId.equals(league.countryId) : league.countryId != null) return false;
        if (club != null ? !club.equals(league.club) : league.club != null) return false;
        if (createdAt != null ? !createdAt.equals(league.createdAt) : league.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(league.updatedAt) : league.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
//        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (club != null ? club.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

	@Override
	public String toString() {
		return "League [id=" + id + ", key=" + key + ", title=" + title +  ", club=" + club
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt  +"]";
	}

}
