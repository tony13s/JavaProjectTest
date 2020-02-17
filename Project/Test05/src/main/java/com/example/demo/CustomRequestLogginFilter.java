package com.example.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Slf4j
@Component
public class CustomRequestLogginFilter extends GenericFilterBean {
	private static final Logger LOGGER =  LoggerFactory.getLogger(CustomRequestLogginFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
      throws IOException, ServletException {
        final HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        //final HttpServletResponse currentResponse = (HttpServletResponse) servletResponse;

        StringBuffer requestURL = currentRequest.getRequestURL();
        LOGGER.error("Meesage from tony error");
        LOGGER.warn("Meesage from tony warn");
        LOGGER.info("Meesage from tony info");
        LOGGER.debug("Meesage from tony debug");
        log.info("Request URL: {}", requestURL);
        try {
        	System.out.println("JSON TONY:");
            chain.doFilter(currentRequest, servletResponse);
        } finally {
            //int status = currentResponse.getStatus();
            //log.info("Response status: {}", status);
        }
    }
}