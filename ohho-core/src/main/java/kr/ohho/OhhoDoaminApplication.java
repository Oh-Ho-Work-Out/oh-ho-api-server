package kr.ohho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OhhoDoaminApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application,domain");
        SpringApplication.run(OhhoDoaminApplication.class, args);
    }
}
