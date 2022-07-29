package com.odeyalo.analog.netflix.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.odeyalo.support")
@PropertySource("classpath:clients.local.properties")
@EnableAsync(proxyTargetClass = true)
public class VideoMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(VideoMicroserviceApplication.class, args);
    }
}
