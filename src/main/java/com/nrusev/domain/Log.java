package com.nrusev.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by nikolayrusev on 3/10/16.
 */
@Entity
@Table(name = "logs")
public class Log {
    private Long id;
    private String msg;
    private String level;
    private String app;
    private String tag;
    private Long pid;
    private Long tid;
    private String ts;
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
    @Column(name = "msg", nullable = false, length = -1)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Basic
    @Column(name = "level", nullable = false, length = -1)
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "app", nullable = true, length = -1)
    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Basic
    @Column(name = "tag", nullable = true, length = -1)
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Basic
    @Column(name = "pid", nullable = true)
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "tid", nullable = true)
    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Basic
    @Column(name = "ts", nullable = true, length = -1)
    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
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

        Log log = (Log) o;

        if (id != null ? !id.equals(log.id) : log.id != null) return false;
        if (msg != null ? !msg.equals(log.msg) : log.msg != null) return false;
        if (level != null ? !level.equals(log.level) : log.level != null) return false;
        if (app != null ? !app.equals(log.app) : log.app != null) return false;
        if (tag != null ? !tag.equals(log.tag) : log.tag != null) return false;
        if (pid != null ? !pid.equals(log.pid) : log.pid != null) return false;
        if (tid != null ? !tid.equals(log.tid) : log.tid != null) return false;
        if (ts != null ? !ts.equals(log.ts) : log.ts != null) return false;
        if (createdAt != null ? !createdAt.equals(log.createdAt) : log.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(log.updatedAt) : log.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (msg != null ? msg.hashCode() : 0);
        result = 31 * result + (level != null ? level.hashCode() : 0);
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (tid != null ? tid.hashCode() : 0);
        result = 31 * result + (ts != null ? ts.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
