package com.heima.article;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 12:46
 * @Description: com.heima.article
 * @version: 1.0
 */
@SpringBootApplication
@MapperScan("com.heima.article.mapper")
@EnableDiscoveryClient
public class ArticleApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleApplication.class, args);
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
