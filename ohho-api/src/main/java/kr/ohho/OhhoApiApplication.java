package kr.ohho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class OhhoApiApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,api");
        SpringApplication.run(OhhoApiApplication.class, args);
    }
}
