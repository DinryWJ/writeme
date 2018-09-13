package com.zust.writeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class WritemeEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(WritemeEurekaApplication.class, args);
    }
}
