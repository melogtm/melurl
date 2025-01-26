package com.melogtm.tinyurl.config.interceptor;

import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Duration;

@Component
public class RateLimitingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RateLimitingInterceptor.class);
    private final Bucket bucket;

    public RateLimitingInterceptor() {
        this.bucket = Bucket.builder().addLimit(l -> l.capacity(10)
                .refillGreedy(10, Duration.ofMinutes(10))).build();
    }

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) {
        if (!bucket.tryConsume(1)) {
            logger.warn("Rate limit exceeded with IP: {}", request.getRemoteAddr());
            response.setStatus(429);
            return false;
        }
        return true;
    }
}
