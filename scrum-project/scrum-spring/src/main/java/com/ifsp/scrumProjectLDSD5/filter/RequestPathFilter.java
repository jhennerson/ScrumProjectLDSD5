package com.ifsp.scrumProjectLDSD5.filter;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@Component
@WebFilter("/*")
public class RequestPathFilter implements Filter {
    private static ThreadLocal<String> requestPath = new ThreadLocal<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            requestPath.set(((HttpServletRequest) request).getRequestURI());
            chain.doFilter(request, response);
        } finally {
            requestPath.remove();
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Método de inicialização do filtro (pode ser deixado vazio)
    }

    @Override
    public void destroy() {
        // Método de destruição do filtro (pode ser deixado vazio)
    }

    public static String getRequestPath() {
        return requestPath.get();
    }
}
