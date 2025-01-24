package com.melogtm.tinyurl.repository;

import com.melogtm.tinyurl.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UrlRepo extends JpaRepository<Url, UUID>{
}
