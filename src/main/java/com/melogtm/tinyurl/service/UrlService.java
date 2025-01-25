package com.melogtm.tinyurl.service;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import com.melogtm.tinyurl.exceptions.UrlNotFoundException;
import com.melogtm.tinyurl.repository.UrlRepo;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UrlService {

    private final UrlRepo repository;

    public UrlService(UrlRepo repository) {
        this.repository = repository;
    }

    public String getOriginalUrl(String shortUrl) throws UrlNotFoundException {
        String long_url = repository.findOriginalUrl(shortUrl);

        if (long_url == null) {
            throw new UrlNotFoundException("Short URL not found");
        }

        repository.incrementCount(shortUrl);

        return long_url;
    }

    public Url shortenUrl(UrlRequestDTO urlRequestDTO) throws UrlAlreadyExistsException {

        String check_url = repository.findOriginalUrl(urlRequestDTO.shortUrl());
        if (check_url != null) {
            throw new UrlAlreadyExistsException("Short URL already exists");
        }

        Url new_url = new Url();

        new_url.setLongUrl(urlRequestDTO.longUrl());
        new_url.setShortUrl(urlRequestDTO.shortUrl());
        new_url.setCreatedAt(new Date());
        new_url.setCount(0);

        repository.save(new_url);

        return new_url;
    }
}
