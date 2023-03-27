package com.heima.app.filter;

import com.heima.model.user.pojos.ApUser;
import com.heima.utils.common.JwtUtils;
import com.heima.utils.common.Payload;
import com.heima.utils.common.RsaUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.security.PublicKey;

/**
 * @author zhangqin
 * @Date: 2023/3/27 - 03 - 27 - 9:34
 * @Description: com.heima.app.filter
 * @version: 1.0
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    @Value("${leadnews.jwt.publicKeyPath}")
    public String publicKeyPath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求和响应
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.获取路径
        String path = request.getURI().getPath();

        //3.判断路径是否放行
        if (path.contains("login")) {
            //放行
            return chain.filter(exchange);
        }
        //4.判断是否拥有  token
        //4.1从头部获取token

        String token = request.getHeaders().getFirst("token");
        //没有权限访问
        if (token == null) {
            //为空不合法
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            //终止请求
            return response.setComplete();
        }
        //5解析token
        try {
            PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
            //验证token是否合法
            Payload<ApUser> infoFromToken = JwtUtils.getInfoFromToken(token, publicKey, ApUser.class);

            ApUser user = infoFromToken.getInfo();
            //设置到请求头中
            request.mutate().header("userId", user.getId().toString());
            //放行
            return chain.filter(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
