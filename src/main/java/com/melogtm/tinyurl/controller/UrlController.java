package com.melogtm.tinyurl.controller;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import com.melogtm.tinyurl.domain.UrlResponseDTO;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import com.melogtm.tinyurl.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
@Validated
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@Valid @ModelAttribute UrlRequestDTO urlRequestDTO) throws UrlAlreadyExistsException {
        Url new_url = urlService.shortenUrl(urlRequestDTO);
        return ResponseEntity.ok(new_url);
    }

    @GetMapping("/{short_url}")
    public ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String short_url) {
        return ResponseEntity.ok(new UrlResponseDTO(urlService.getOriginalUrl(short_url)));
    }
}
