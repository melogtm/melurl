package com.melogtm.tinyurl.repository;

import com.melogtm.tinyurl.domain.Url;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UrlRepo extends JpaRepository<Url, UUID>{

    @Query("SELECT u.longUrl FROM Url u WHERE u.shortUrl = :shortUrl")
    String findOriginalUrl(String shortUrl);

    @Modifying
    @Transactional
    @Query("UPDATE Url u SET u.count = u.count + 1 WHERE u.shortUrl = :shortUrl")
    void incrementCount(String shortUrl);

}
