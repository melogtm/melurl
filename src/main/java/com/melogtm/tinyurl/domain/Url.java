package com.melogtm.tinyurl.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Table(name = "url")
@Entity
public class Url implements Serializable {
    public Url() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "shorten_url", unique = true)
    private String shortUrl;

    @Column(name = "original_url")
    private String longUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column (name = "count")
    private Integer count;


    public Integer getId() {
        return id;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
