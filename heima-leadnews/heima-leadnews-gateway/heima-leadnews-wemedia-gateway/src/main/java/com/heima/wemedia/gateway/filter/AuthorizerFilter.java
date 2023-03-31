package com.heima.wemedia.gateway.filter;

import com.heima.model.wemedia.pojos.WmUser;
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
 * @Date: 2023/3/28 - 03 - 28 - 20:23
 * @Description: com.heima.wemedia.gateway.filter
 * @version: 1.0
 */
@Component
public class AuthorizerFilter implements GlobalFilter,Ordered {
    @Value("${leadnews.jwt.publicKeyPath}")
    private  String publicKeyPath;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求和响应
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        //2.获取请求路径
        String path = request.getURI().getPath();

        //3.登入放行
        if (path.contains("login")) {
            return chain.filter(exchange);
        }
        //4.拦截其他路径
        //5.解析是否携带token
        String token = request.getHeaders().getFirst("token");
        //5.2没有拒绝访问
        if (token == null) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        //5.1携带解析token
        try {
            PublicKey publicKey = RsaUtils.getPublicKey(publicKeyPath);
            Payload<WmUser> infoFromToken = JwtUtils.getInfoFromToken(token, publicKey, WmUser.class);
            WmUser info = infoFromToken.getInfo();
            request.mutate().header("userId",info.getId().toString());
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
