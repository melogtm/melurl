package com.melogtm.tinyurl.service;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import com.melogtm.tinyurl.repository.UrlRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlService {

    private final UrlRepo repository;

    public UrlService(UrlRepo repository) {
        this.repository = repository;
    }

    public Url shortenUrl(UrlRequestDTO urlRequestDTO) {
        Url new_url = new Url();

        new_url.setLongUrl(urlRequestDTO.longUrl());
        new_url.setShortUrl(urlRequestDTO.shortUrl());
        new_url.setCreatedAt(new Date());

        repository.save(new_url);

        return new_url;
    }

    public String getOriginalUrl(String shortUrl) {
        return repository.findOriginalUrl(shortUrl);
    }
}
