package com.melogtm.tinyurl.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Table(name = "url")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Url {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "shorten_url")
    private String shortUrl;

    @Column(name = "original_url")
    private String longUrl;

    @Column(name = "created_at")
    private Date createdAt;

    @Column (name = "count")
    private Integer count;
}
