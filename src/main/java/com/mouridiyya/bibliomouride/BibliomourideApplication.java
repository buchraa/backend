package com.mouridiyya.bibliomouride;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BibliomourideApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliomourideApplication.class, args);
	}

}
