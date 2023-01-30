package com.github.tatanka27.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VotingApplication {

    public static void main(String[] args) {
        System.setProperty("file.encoding","UTF-8");
        SpringApplication.run(VotingApplication.class, args);
    }

}
