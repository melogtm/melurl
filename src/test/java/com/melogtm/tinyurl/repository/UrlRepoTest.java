package com.melogtm.tinyurl.repository;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class UrlRepoTest {

    @Autowired
    EntityManager em;

    @Autowired
    UrlRepo ur;

    @Test
    @DisplayName("Find an existing original URL")
    void findOriginalUrlSuccessful() {

        UrlRequestDTO urlRequestDTO = new UrlRequestDTO("joseph", "https://www.youtube.com/",
                0L, 0);

        this.createUrl(urlRequestDTO);

        Optional<String> longUrl = this.ur.findOriginalUrl("joseph");

        assertThat(longUrl.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Did not find an original URL")
    void findOriginalUrlNotFound() {
        Optional<String> longUrl = this.ur.findOriginalUrl("gabriel");

        assertThat(longUrl.isEmpty()).isTrue();
    }

    private void createUrl(UrlRequestDTO urlRequestDTO) {
        Url new_url = new Url();

        new_url.setLongUrl(urlRequestDTO.longUrl());
        new_url.setShortUrl(urlRequestDTO.shortUrl());
        new_url.setCreatedAt(new Date());
        new_url.setCount(0);

        this.em.persist(new_url);
    }
}