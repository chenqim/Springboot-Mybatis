package com.summer.filter;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
*  解决跨域问题的过滤器
*
*  1、@Component 这个注解的目的是将CrossOriginFilter交给容器来处理。也就是让CrossOriginFilter起作用
*  2、@ServletComponentScan 这个使用来扫描@WebFilter 的让@WebFilter起作用。当然对于servlet线管注解也是可以的。这个@ServletComponentScan最好卸载Apllication这个上面，通用配置。我这里因为只有一个Filter所以没有写在Application上面。
*  3、@WebFilter 这个用处显而易见，针对于什么链接做过滤，filter的名称是为什么。
* */
@Component
@ServletComponentScan
@WebFilter(filterName = "CrossOriginFilter", urlPatterns = "/*")
public class CrossOriginFilter implements Filter {

    private static Logger logger = Logger.getLogger(CrossOriginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter...");
        HttpServletResponse rsp = (HttpServletResponse) servletResponse;
        rsp.setHeader("Access-Control-Allow-Origin", "*");
        rsp.setHeader("Access-Control-Allow-Headers", "Content-Type,token,X-Requested-With");
        rsp.setHeader("Access-Control-Allow-Credentials", "true");
        rsp.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
        rsp.setHeader("X-Powered-By", " 3.2.1");
        rsp.setHeader("Content-Type", "application/json;charset=utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
