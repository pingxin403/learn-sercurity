package com.hyp.learn.dsm.uaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.dsm.uaa
 * hyp create at 19-12-25
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class);
    }
}
