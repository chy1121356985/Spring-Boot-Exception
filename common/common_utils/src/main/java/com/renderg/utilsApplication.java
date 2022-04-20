package com.renderg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author chenhy
 */
@EnableScheduling
@SpringBootApplication
public class utilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(utilsApplication.class, args);
    }
}
