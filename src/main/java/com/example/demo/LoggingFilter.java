package com.example.demo;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(final ServletRequest req,
                         final ServletResponse res,
                         final FilterChain chain) throws IOException, ServletException {
        final String url = ((HttpServletRequest) req).getRequestURI();
        log.warn("== {}", url);

        if(url.matches("/(health|.+\\.(ico|js))")) {
            req.setAttribute("ignoreLogging", true);
        }

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
