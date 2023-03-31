package com.heima.wemedia.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangqin
 * @Date: 2023/3/28 - 03 - 28 - 20:16
 * @Description: com.heima.wemedia.gateway
 * @version: 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class WemediaGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(WemediaGatewayApplication.class,args);
    }
}
