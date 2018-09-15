package com.zust.writeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author YFZX-WJJ-1778
 */
@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
@EnableScheduling
public class WritemeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WritemeServiceApplication.class, args);
    }
}
