package com.heima.common.minio;

import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangqin
 * @Date: 2023/2/21 - 02 - 21 - 15:49
 * @Description: 初始化MinIo客户端对象
 * @version: 1.0
 */
@EnableConfigurationProperties(MinIOConfigProperties.class)
@Configuration
public class MinIOConfiguration {

    @Autowired
    private MinIOConfigProperties minIOConfigProperties;
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint(minIOConfigProperties.getEndpoint())
                .credentials(minIOConfigProperties.getAccessKey(), minIOConfigProperties.getSecretKey())
                .build();
    }

}
