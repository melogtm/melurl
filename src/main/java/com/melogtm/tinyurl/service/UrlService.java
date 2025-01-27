package com.melogtm.tinyurl.service;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import com.melogtm.tinyurl.exceptions.UrlNotFoundException;
import com.melogtm.tinyurl.repository.UrlRepo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlRepo repository;

    public UrlService(UrlRepo repository) {
        this.repository = repository;
    }

    @Cacheable(value = "urls", key = "#shortUrl")
    public Optional<String> getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        Optional<String> long_url = repository.findOriginalUrl(shortUrl);

        if (long_url.isEmpty()) {
            throw new UrlNotFoundException("Short URL not found");
        }

        repository.incrementCount(shortUrl);

        return long_url;
    }

    public Url shortenUrl(UrlRequestDTO urlRequestDTO) throws UrlAlreadyExistsException {

        Optional<String> check_url = repository.findOriginalUrl(urlRequestDTO.shortUrl());

        if (check_url.isPresent()) throw new UrlAlreadyExistsException("Short URL already exists");

        Url new_url = new Url();

        new_url.setLongUrl(urlRequestDTO.longUrl());
        new_url.setShortUrl(urlRequestDTO.shortUrl());
        new_url.setCreatedAt(new Date());
        new_url.setCount(0);

        repository.save(new_url);

        return new_url;
    }
}
