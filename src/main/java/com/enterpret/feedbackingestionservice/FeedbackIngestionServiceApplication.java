package com.enterpret.feedbackingestionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FeedbackIngestionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedbackIngestionServiceApplication.class, args);
    }

}
