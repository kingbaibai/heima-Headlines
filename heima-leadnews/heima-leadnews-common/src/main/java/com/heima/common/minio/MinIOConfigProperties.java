package com.heima.common.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangqin
 * @Date: 2023/2/21 - 02 - 21 - 15:48
 * @Description: com.heima.model.minio
 * @version: 1.0
 */
@ConfigurationProperties(prefix = "minio")
@Data
public class MinIOConfigProperties {
    private String accessKey;//账户名称
    private String secretKey;//账户密码
    private String endpoint;//MinIO连接地址   http://192.168.66.133:9000/

    private String bucket;//桶名称
    private String readPath;//访问文件的地址  http://192.168.66.133:9000/
}
