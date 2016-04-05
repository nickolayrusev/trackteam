package com.nrusev.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "countries")
public class Country {
    private Long id;
    private String name;
    private String slug;
    private String key;
    private Long placeId;
    private String code;
    private String altNames;
    private String histNames;
    private Long pop;
    private Long area;
    private Continent continent;
    private Long countryId;
    private Boolean s;
    private Boolean c;
    private Boolean d;
    private Boolean m;
    private String motor;
    private String alpha2;
    private String alpha3;
    private String num;
    private String fifa;
    private String ioc;
    private String fips;
    private String wikipedia;
    private String net;
    private Date createdAt;
    private Date updatedAt;
    private Set<Team> teams = new HashSet<Team>(0);


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
    @Column(name = "code", nullable = false, length = -1)
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
    @Column(name = "hist_names", nullable = true, length = -1)
    public String getHistNames() {
        return histNames;
    }

    public void setHistNames(String histNames) {
        this.histNames = histNames;
    }

    @Basic
    @Column(name = "pop", nullable = false)
    public Long getPop() {
        return pop;
    }

    public void setPop(Long pop) {
        this.pop = pop;
    }


    @Basic
    @Column(name = "area", nullable = false)
    public Long getArea() {
        return area;
    }

    public void setArea(Long area) {
        this.area = area;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id", nullable = false)
    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
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
    @Column(name = "s", nullable = false)
    public Boolean getS() {
        return s;
    }

    public void setS(Boolean s) {
        this.s = s;
    }

    @Basic
    @Column(name = "c", nullable = false)
    public Boolean getC() {
        return c;
    }

    public void setC(Boolean c) {
        this.c = c;
    }

    @Basic
    @Column(name = "d", nullable = false)
    public Boolean getD() {
        return d;
    }

    public void setD(Boolean d) {
        this.d = d;
    }

    @Basic
    @Column(name = "m", nullable = false)
    public Boolean getM() {
        return m;
    }

    public void setM(Boolean m) {
        this.m = m;
    }

    @Basic
    @Column(name = "motor", nullable = true, length = -1)
    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    @Basic
    @Column(name = "alpha2", nullable = true, length = -1)
    public String getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

    @Basic
    @Column(name = "alpha3", nullable = true, length = -1)
    public String getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    @Basic
    @Column(name = "num", nullable = true, length = -1)
    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    @Basic
    @Column(name = "fifa", nullable = true, length = -1)
    public String getFifa() {
        return fifa;
    }

    public void setFifa(String fifa) {
        this.fifa = fifa;
    }

    @Basic
    @Column(name = "ioc", nullable = true, length = -1)
    public String getIoc() {
        return ioc;
    }

    public void setIoc(String ioc) {
        this.ioc = ioc;
    }

    @Basic
    @Column(name = "fips", nullable = true, length = -1)
    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    @Basic
    @Column(name = "net", nullable = true, length = -1)
    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    @Basic
    @Column(name = "wikipedia", nullable = true, length = -1)
    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
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

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "country")
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (id != null ? !id.equals(country.id) : country.id != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        if (slug != null ? !slug.equals(country.slug) : country.slug != null) return false;
        if (key != null ? !key.equals(country.key) : country.key != null) return false;
        if (placeId != null ? !placeId.equals(country.placeId) : country.placeId != null) return false;
        if (code != null ? !code.equals(country.code) : country.code != null) return false;
        if (altNames != null ? !altNames.equals(country.altNames) : country.altNames != null) return false;
        if (histNames != null ? !histNames.equals(country.histNames) : country.histNames != null) return false;
        if (pop != null ? !pop.equals(country.pop) : country.pop != null) return false;
        if (area != null ? !area.equals(country.area) : country.area != null) return false;
        if (continent != null ? !continent.equals(country.continent) : country.continent != null) return false;
        if (countryId != null ? !countryId.equals(country.countryId) : country.countryId != null) return false;
        if (s != null ? !s.equals(country.s) : country.s != null) return false;
        if (c != null ? !c.equals(country.c) : country.c != null) return false;
        if (d != null ? !d.equals(country.d) : country.d != null) return false;
        if (m != null ? !m.equals(country.m) : country.m != null) return false;
        if (motor != null ? !motor.equals(country.motor) : country.motor != null) return false;
        if (alpha2 != null ? !alpha2.equals(country.alpha2) : country.alpha2 != null) return false;
        if (alpha3 != null ? !alpha3.equals(country.alpha3) : country.alpha3 != null) return false;
        if (num != null ? !num.equals(country.num) : country.num != null) return false;
        if (fifa != null ? !fifa.equals(country.fifa) : country.fifa != null) return false;
        if (ioc != null ? !ioc.equals(country.ioc) : country.ioc != null) return false;
        if (fips != null ? !fips.equals(country.fips) : country.fips != null) return false;
        if (net != null ? !net.equals(country.net) : country.net != null) return false;
        if (wikipedia != null ? !wikipedia.equals(country.wikipedia) : country.wikipedia != null) return false;
        if (createdAt != null ? !createdAt.equals(country.createdAt) : country.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(country.updatedAt) : country.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (slug != null ? slug.hashCode() : 0);
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (placeId != null ? placeId.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (altNames != null ? altNames.hashCode() : 0);
        result = 31 * result + (histNames != null ? histNames.hashCode() : 0);
        result = 31 * result + (pop != null ? pop.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (continent != null ? continent.hashCode() : 0);
        result = 31 * result + (countryId != null ? countryId.hashCode() : 0);
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (c != null ? c.hashCode() : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (m != null ? m.hashCode() : 0);
        result = 31 * result + (motor != null ? motor.hashCode() : 0);
        result = 31 * result + (alpha2 != null ? alpha2.hashCode() : 0);
        result = 31 * result + (alpha3 != null ? alpha3.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (fifa != null ? fifa.hashCode() : 0);
        result = 31 * result + (ioc != null ? ioc.hashCode() : 0);
        result = 31 * result + (fips != null ? fips.hashCode() : 0);
        result = 31 * result + (net != null ? net.hashCode() : 0);
        result = 31 * result + (wikipedia != null ? wikipedia.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
