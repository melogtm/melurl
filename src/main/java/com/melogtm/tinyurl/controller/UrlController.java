package com.melogtm.tinyurl.controller;

import com.melogtm.tinyurl.domain.Url;
import com.melogtm.tinyurl.domain.UrlRequestDTO;
import com.melogtm.tinyurl.domain.UrlResponseDTO;
import com.melogtm.tinyurl.exceptions.UrlAlreadyExistsException;
import com.melogtm.tinyurl.exceptions.UrlNotFoundException;
import com.melogtm.tinyurl.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url")
@Validated
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<UrlResponseDTO> shortenUrl(@Valid @ModelAttribute UrlRequestDTO urlRequestDTO)
            throws UrlAlreadyExistsException {
        Url new_url = urlService.shortenUrl(urlRequestDTO);

        UrlResponseDTO urlResponseDTO = new UrlResponseDTO(Optional.ofNullable(new_url.getLongUrl()));

        return ResponseEntity.ok(urlResponseDTO);
    }

    @GetMapping("/{short_url}")
    public ResponseEntity<UrlResponseDTO> getOriginalUrl(@PathVariable String short_url)
            throws UrlNotFoundException {
        return ResponseEntity.ok(new UrlResponseDTO(urlService.getOriginalUrl(short_url)));
    }
}
