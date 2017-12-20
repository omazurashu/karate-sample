package org.om.karate.sample;

import org.om.karate.sample.persistence.User;
import org.om.karate.sample.persistence.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }

    @Bean
    ApplicationRunner init(UserRepository userRepository) {
        return args -> Stream.of("user1", "user2", "user3").forEach(nickName -> {
            User user = new User(nickName);
            userRepository.save(user);
        });
    }
}
