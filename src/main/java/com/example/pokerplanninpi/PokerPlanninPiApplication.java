package com.example.pokerplanninpi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class PokerPlanninPiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokerPlanninPiApplication.class, args);
    }

}
