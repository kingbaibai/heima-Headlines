package com.heima.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangqin
 * @Date: 2023/3/26 - 03 - 26 - 13:30
 * @Description: com.heima.app
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AppGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppGatewayApplication.class, args);
    }
}
