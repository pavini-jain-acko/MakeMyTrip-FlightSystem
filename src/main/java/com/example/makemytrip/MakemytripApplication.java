package com.example.makemytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.example.makemytrip.search.repository","com.example.makemytrip.booking.repository"})
public class MakemytripApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakemytripApplication.class, args);
	}

}
