package com.hyp.learn.ds.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hyp
 * Project name is learn-security
 * Include in com.hyp.learn.ds.order
 * hyp create at 19-12-20
 **/
@SpringBootApplication
@EnableDiscoveryClient
//@EnableHystrix
//@EnableFeignClients(basePackages = {"com.hyp.learn.ds.order"})
public class OrderServer {
    public static void main(String[] args) {
        SpringApplication.run(OrderServer.class);
    }
}
