package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "rounds")
public class Round {
    private Long id;
//    private Long eventId;
    private Event event;
    private String title;
    private String title2;
    private Long pos;
    private Boolean knockout;
    private Date startAt;
    private Date endAt;
    private Boolean auto;
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

//    @Basic
//    @Column(name = "event_id", nullable = false)
//    public Long getEventId() {
//        return eventId;
//    }
//
//    public void setEventId(Long eventId) {
//        this.eventId = eventId;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
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
    @Column(name = "pos", nullable = false)
    public Long getPos() {
        return pos;
    }

    public void setPos(Long pos) {
        this.pos = pos;
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
    @Column(name = "start_at", nullable = false)
    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    @Basic
    @Column(name = "end_at", nullable = true)
    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    @Basic
    @Column(name = "auto", nullable = false)
    public Boolean getAuto() {
        return auto;
    }

    public void setAuto(Boolean auto) {
        this.auto = auto;
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

        Round round = (Round) o;

        if (id != null ? !id.equals(round.id) : round.id != null) return false;
//        if (eventId != null ? !eventId.equals(round.eventId) : round.eventId != null) return false;
        if (title != null ? !title.equals(round.title) : round.title != null) return false;
        if (title2 != null ? !title2.equals(round.title2) : round.title2 != null) return false;
        if (pos != null ? !pos.equals(round.pos) : round.pos != null) return false;
        if (knockout != null ? !knockout.equals(round.knockout) : round.knockout != null) return false;
        if (startAt != null ? !startAt.equals(round.startAt) : round.startAt != null) return false;
        if (endAt != null ? !endAt.equals(round.endAt) : round.endAt != null) return false;
        if (auto != null ? !auto.equals(round.auto) : round.auto != null) return false;
        if (createdAt != null ? !createdAt.equals(round.createdAt) : round.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(round.updatedAt) : round.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
//        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (title2 != null ? title2.hashCode() : 0);
        result = 31 * result + (pos != null ? pos.hashCode() : 0);
        result = 31 * result + (knockout != null ? knockout.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        result = 31 * result + (auto != null ? auto.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
