package com.melogtm.tinyurl.domain;

public record UrlRequestDTO(String shortUrl, String longUrl, Long createdAt, Integer count) {
}
