package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "games")
public class Game {
    private Long id;
    private String key;
    private Long roundId;
    private Long pos;
    private Long groupId;
    private Team homeTeam;
    private Team visitorTeam;
    private Date playAt;
    private Boolean postponed;
    private Date playAtV2;
    private Date playAtV3;
    private Long groundId;
    private Long cityId;
    private Boolean knockout;
    private Boolean home;
    private Long score1;
    private Long score2;
    private Long score1Et;
    private Long score2Et;
    private Long score1P;
    private Long score2P;
    private Long score1I;
    private Long score2I;
    private Long score1Ii;
    private Long score2Ii;
    private Long nextGameId;
    private Long prevGameId;
    private Long winner;
    private Long winner90;
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
    @Column(name = "key", nullable = true, length = -1)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Basic
    @Column(name = "round_id", nullable = false)
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    @Basic
    @Column(name = "pos", nullable = false)
    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "play_at", nullable = false)
    public Date getPlayAt() {
        return playAt;
    }

    public void setPlayAt(Date playAt) {
        this.playAt = playAt;
    }

    @Basic
    @Column(name = "postponed", nullable = false)
    public Boolean getPostponed() {
        return postponed;
    }

    public void setPostponed(Boolean postponed) {
        this.postponed = postponed;
    }

    @Basic
    @Column(name = "play_at_v2", nullable = true)
    public Date getPlayAtV2() {
        return playAtV2;
    }

    public void setPlayAtV2(Date playAtV2) {
        this.playAtV2 = playAtV2;
    }

    @Basic
    @Column(name = "play_at_v3", nullable = true)
    public Date getPlayAtV3() {
        return playAtV3;
    }

    public void setPlayAtV3(Date playAtV3) {
        this.playAtV3 = playAtV3;
    }

    @Basic
    @Column(name = "ground_id", nullable = true)
    public Long getGroundId() {
        return groundId;
    }

    public void setGroundId(Long groundId) {
        this.groundId = groundId;
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
    @Column(name = "knockout", nullable = false)
    public Boolean getKnockout() {
        return knockout;
    }

    public void setKnockout(Boolean knockout) {
        this.knockout = knockout;
    }

    @Basic
    @Column(name = "home", nullable = false)
    public Boolean getHome() {
        return home;
    }

    public void setHome(Boolean home) {
        this.home = home;
    }

    @Basic
    @Column(name = "score1", nullable = true)
    public Long getScore1() {
        return score1;
    }

    public void setScore1(Long score1) {
        this.score1 = score1;
    }

    @Basic
    @Column(name = "score2", nullable = true)
    public Long getScore2() {
        return score2;
    }

    public void setScore2(Long score2) {
        this.score2 = score2;
    }

    @Basic
    @Column(name = "score1et", nullable = true)
    public Long getScore1Et() {
        return score1Et;
    }

    public void setScore1Et(Long score1Et) {
        this.score1Et = score1Et;
    }

    @Basic
    @Column(name = "score2et", nullable = true)
    public Long getScore2Et() {
        return score2Et;
    }

    public void setScore2Et(Long score2Et) {
        this.score2Et = score2Et;
    }

    @Basic
    @Column(name = "score1p", nullable = true)
    public Long getScore1P() {
        return score1P;
    }

    public void setScore1P(Long score1P) {
        this.score1P = score1P;
    }

    @Basic
    @Column(name = "score2p", nullable = true)
    public Long getScore2P() {
        return score2P;
    }

    public void setScore2P(Long score2P) {
        this.score2P = score2P;
    }

    @Basic
    @Column(name = "score1i", nullable = true)
    public Long getScore1I() {
        return score1I;
    }

    public void setScore1I(Long score1I) {
        this.score1I = score1I;
    }

    @Basic
    @Column(name = "score2i", nullable = true)
    public Long getScore2I() {
        return score2I;
    }

    public void setScore2I(Long score2I) {
        this.score2I = score2I;
    }

    @Basic
    @Column(name = "score1ii", nullable = true)
    public Long getScore1Ii() {
        return score1Ii;
    }

    public void setScore1Ii(Long score1Ii) {
        this.score1Ii = score1Ii;
    }

    @Basic
    @Column(name = "score2ii", nullable = true)
    public Long getScore2Ii() {
        return score2Ii;
    }

    public void setScore2Ii(Long score2Ii) {
        this.score2Ii = score2Ii;
    }

    @Basic
    @Column(name = "next_game_id", nullable = true)
    public Long getNextGameId() {
        return nextGameId;
    }

    public void setNextGameId(Long nextGameId) {
        this.nextGameId = nextGameId;
    }

    @Basic
    @Column(name = "prev_game_id", nullable = true)
    public Long getPrevGameId() {
        return prevGameId;
    }

    public void setPrevGameId(Long prevGameId) {
        this.prevGameId = prevGameId;
    }

    @Basic
    @Column(name = "winner", nullable = true)
    public Long getWinner() {
        return winner;
    }

    public void setWinner(Long winner) {
        this.winner = winner;
    }

    @Basic
    @Column(name = "winner90", nullable = true)
    public Long getWinner90() {
        return winner90;
    }

    public void setWinner90(Long winner90) {
        this.winner90 = winner90;
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

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="team1_id")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="team2_id")
    public Team getVisitorTeam() {
        return visitorTeam;
    }

    public void setVisitorTeam(Team visitorTeam) {
        this.visitorTeam = visitorTeam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (id != null ? !id.equals(game.id) : game.id != null) return false;
        if (key != null ? !key.equals(game.key) : game.key != null) return false;
        if (roundId != null ? !roundId.equals(game.roundId) : game.roundId != null) return false;
        if (pos != null ? !pos.equals(game.pos) : game.pos != null) return false;
        if (groupId != null ? !groupId.equals(game.groupId) : game.groupId != null) return false;
//        if (team1Id != null ? !team1Id.equals(game.team1Id) : game.team1Id != null) return false;
//        if (team2Id != null ? !team2Id.equals(game.team2Id) : game.team2Id != null) return false;
        if (playAt != null ? !playAt.equals(game.playAt) : game.playAt != null) return false;
        if (postponed != null ? !postponed.equals(game.postponed) : game.postponed != null) return false;
        if (playAtV2 != null ? !playAtV2.equals(game.playAtV2) : game.playAtV2 != null) return false;
        if (playAtV3 != null ? !playAtV3.equals(game.playAtV3) : game.playAtV3 != null) return false;
        if (groundId != null ? !groundId.equals(game.groundId) : game.groundId != null) return false;
        if (cityId != null ? !cityId.equals(game.cityId) : game.cityId != null) return false;
        if (knockout != null ? !knockout.equals(game.knockout) : game.knockout != null) return false;
        if (home != null ? !home.equals(game.home) : game.home != null) return false;
        if (score1 != null ? !score1.equals(game.score1) : game.score1 != null) return false;
        if (score2 != null ? !score2.equals(game.score2) : game.score2 != null) return false;
        if (score1Et != null ? !score1Et.equals(game.score1Et) : game.score1Et != null) return false;
        if (score2Et != null ? !score2Et.equals(game.score2Et) : game.score2Et != null) return false;
        if (score1P != null ? !score1P.equals(game.score1P) : game.score1P != null) return false;
        if (score2P != null ? !score2P.equals(game.score2P) : game.score2P != null) return false;
        if (score1I != null ? !score1I.equals(game.score1I) : game.score1I != null) return false;
        if (score2I != null ? !score2I.equals(game.score2I) : game.score2I != null) return false;
        if (score1Ii != null ? !score1Ii.equals(game.score1Ii) : game.score1Ii != null) return false;
        if (score2Ii != null ? !score2Ii.equals(game.score2Ii) : game.score2Ii != null) return false;
        if (nextGameId != null ? !nextGameId.equals(game.nextGameId) : game.nextGameId != null) return false;
        if (prevGameId != null ? !prevGameId.equals(game.prevGameId) : game.prevGameId != null) return false;
        if (winner != null ? !winner.equals(game.winner) : game.winner != null) return false;
        if (winner90 != null ? !winner90.equals(game.winner90) : game.winner90 != null) return false;
        if (createdAt != null ? !createdAt.equals(game.createdAt) : game.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(game.updatedAt) : game.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        result = 31 * result + (roundId != null ? roundId.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (groupId != null ? groupId.hashCode() : 0);
//        result = 31 * result + (team1Id != null ? team1Id.hashCode() : 0);
//        result = 31 * result + (team2Id != null ? team2Id.hashCode() : 0);
        result = 31 * result + (playAt != null ? playAt.hashCode() : 0);
        result = 31 * result + (postponed != null ? postponed.hashCode() : 0);
        result = 31 * result + (playAtV2 != null ? playAtV2.hashCode() : 0);
        result = 31 * result + (playAtV3 != null ? playAtV3.hashCode() : 0);
        result = 31 * result + (groundId != null ? groundId.hashCode() : 0);
        result = 31 * result + (cityId != null ? cityId.hashCode() : 0);
        result = 31 * result + (knockout != null ? knockout.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (score1 != null ? score1.hashCode() : 0);
        result = 31 * result + (score2 != null ? score2.hashCode() : 0);
        result = 31 * result + (score1Et != null ? score1Et.hashCode() : 0);
        result = 31 * result + (score2Et != null ? score2Et.hashCode() : 0);
        result = 31 * result + (score1P != null ? score1P.hashCode() : 0);
        result = 31 * result + (score2P != null ? score2P.hashCode() : 0);
        result = 31 * result + (score1I != null ? score1I.hashCode() : 0);
        result = 31 * result + (score2I != null ? score2I.hashCode() : 0);
        result = 31 * result + (score1Ii != null ? score1Ii.hashCode() : 0);
        result = 31 * result + (score2Ii != null ? score2Ii.hashCode() : 0);
        result = 31 * result + (nextGameId != null ? nextGameId.hashCode() : 0);
        result = 31 * result + (prevGameId != null ? prevGameId.hashCode() : 0);
        result = 31 * result + (winner != null ? winner.hashCode() : 0);
        result = 31 * result + (winner90 != null ? winner90.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

}
