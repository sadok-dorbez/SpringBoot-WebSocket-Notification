package com.example.pokerplanninpi;

import com.example.pokerplanninpi.services.ImageService;
import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableWebSocketMessageBroker
public class PokerPlanninPiApplication implements CommandLineRunner {
    @Resource
    ImageService imageService;
    public static void main(String[] args) {
        SpringApplication.run(PokerPlanninPiApplication.class, args);
    }
    @Override
    public void run(String... arg) throws Exception {
//    storageService.deleteAll();
        imageService.init();
    }
}
