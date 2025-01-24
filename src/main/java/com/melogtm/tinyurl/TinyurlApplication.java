package com.melogtm.tinyurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
@Profile("dev")
public class TinyurlApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinyurlApplication.class, args);
	}

}
