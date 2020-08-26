package com.daenisches_bettenlager_programming_task.SpringLoginPage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLoginPageApplication implements CommandLineRunner {

    @Value("${server.port}")
    private int localServerPort;

    public static void main(String[] args) {
        SpringApplication.run(SpringLoginPageApplication.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Web Application runs on local port: " + localServerPort);
    }
}
