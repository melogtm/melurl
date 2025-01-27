package com.melogtm.tinyurl.domain;

import java.util.Optional;

public record UrlResponseDTO(Optional<String> longUrl) {
}
