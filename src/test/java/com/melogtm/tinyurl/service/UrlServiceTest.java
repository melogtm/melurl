package com.melogtm.tinyurl.service;

import com.melogtm.tinyurl.exceptions.UrlNotFoundException;
import com.melogtm.tinyurl.repository.UrlRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UrlServiceTest {

    @Mock
    UrlRepo repository;

    @Autowired
    @InjectMocks
    UrlService urlService;

    @BeforeEach
    void start() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Original URL not found")
    void getOriginalUrlNotFound() {
        when(repository.findOriginalUrl(anyString())).thenReturn(Optional.empty());

        UrlNotFoundException thrown = assertThrows(UrlNotFoundException.class,
                () -> this.urlService.getOriginalUrl(""));

        assertEquals("Short URL not found", thrown.getMessage());
    }

}