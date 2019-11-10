package com.springboot.demo.auth.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * XssSqlFilter
 *
 * @author Jim
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "xssFilter", urlPatterns = "/*", asyncSupported = true)
public class XssSqlFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("xssFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        XssSqlHttpServletRequestWrapper xssSqlHttpServletRequestWrapper = new XssSqlHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        filterChain.doFilter(xssSqlHttpServletRequestWrapper, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("xssFilter destroy");
    }
}
