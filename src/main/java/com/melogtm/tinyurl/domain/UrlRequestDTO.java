package com.melogtm.tinyurl.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UrlRequestDTO(
        @NotNull(message = "Shorten URL is required")
        @Size(min = 3, max = 15, message = "Shorten URL must be between 3 and 15 characters")
        @Pattern(regexp = "^\\S*$", message = "Shorten URL must not contain spaces")
        String shortUrl,

        @NotNull(message = "Original URL is required")
        @Pattern(regexp = "^(https?://)(www\\.)?[a-zA-Z0-9-]+(\\.[a-zA-Z]{2,63}){1,3}(/[a-zA-Z0-9._~:/?#\\[\\]@!$&'()*+,;=-]*)?$",
                message = "URL inválida")
        String longUrl,

        Long createdAt,
        Integer count) {
}
