package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

@Slf4j
@WebFilter(filterName = "logincheck", urlPatterns = "/*")
public class filter implements Filter {
    //创建一个匹配器
    public static AntPathMatcher PATH_MATCHER = new AntPathMatcher();


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", "http://localhost:8080");
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        res.addHeader("Access-Control-Allow-Methods", "*,DELETE");
        String requestURI = request.getRequestURI();
        HttpSession httpSession = request.getSession(false);
        String[] matcher = new String[]{
                "/login/*",
                "/login",
        };

        for (int i = 0; i < matcher.length; i++) {
            if (PATH_MATCHER.match(matcher[i], requestURI)) {
                filterChain.doFilter(request, res);
                return;
            }
        }

        if (request.getSession().getAttribute("user") != null) {
            filterChain.doFilter(request, res);
            return;
        }
        log.info("---------------");
        System.out.println(request.getMethod());
        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("user"));
        log.info("-----------------");
        log.info("拦截{}", requestURI);
        res.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

    }
}
