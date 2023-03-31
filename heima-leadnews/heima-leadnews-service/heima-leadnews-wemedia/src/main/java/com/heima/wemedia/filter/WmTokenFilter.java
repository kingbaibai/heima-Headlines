package com.heima.wemedia.filter;

import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.ThreadLocalUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于获取当前登录用户信息过滤器
 */
@Component
@WebFilter(filterName = "wmTokenFilter",urlPatterns = "/*")
public class WmTokenFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //获取用户的请求头
        String userId = request.getHeader("userId");

        if(StringUtils.isNotEmpty(userId)){
            //把用户信息存入ThreadLocal
            WmUser user = new WmUser();
            user.setId(Integer.valueOf(userId));
            ThreadLocalUtils.set(user);
        }

        try {
            //放行
            filterChain.doFilter(request,response);
        } finally {
            //不论业务执行成功与否，都必须把数据从ThreadLocal移除
            ThreadLocalUtils.remove();
        }
    }
}


