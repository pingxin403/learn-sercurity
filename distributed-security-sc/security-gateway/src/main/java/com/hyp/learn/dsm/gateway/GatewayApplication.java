package com.hyp.learn.dsm.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.dsm.gateway
 * hyp create at 19-12-25
 **/
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class);
    }
}
