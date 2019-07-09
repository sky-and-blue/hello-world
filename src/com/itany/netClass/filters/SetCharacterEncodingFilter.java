package com.itany.netClass.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itany.netClass.constant.Constant;

/**
 * 字符集过滤器
 *
 * @author teacher
 * @date 2018-8-22
 */
@WebFilter(
        filterName = "encodingFilter",
        urlPatterns = "/*",
        initParams = {@WebInitParam(name = "encoding", value = "UTF-8")}
)
public class SetCharacterEncodingFilter implements Filter {

    private String encoding;

    @Override
    public void destroy() {
        this.encoding = null;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {
        //一般处理方式
        HttpServletResponse response = (HttpServletResponse)res;
        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getServletPath();
        System.out.println("servletPath=" + servletPath);
        //一般处理方式
        request.setCharacterEncoding(this.encoding);
        response.setCharacterEncoding(this.encoding);
        response.setContentType("application/json");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
        if (this.encoding == null) {
            this.encoding = Constant.FILTER_CHARSET_UTF8;
        }
    }

}
