package com.htp.learn.dsm.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.htp.learn.dsm.discovery
 * hyp create at 19-12-25
 **/
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplication {
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class);
    }
}
