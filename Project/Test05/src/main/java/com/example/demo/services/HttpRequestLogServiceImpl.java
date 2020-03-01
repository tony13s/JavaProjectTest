package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.model.HttpRequestLog;
import com.example.demo.repository.HttpRequestLogRepository;


public class HttpRequestLogServiceImpl implements HttpRequestLogService{
	
	private HttpRequestLogRepository httpRequestLogRepository;
	
	@Autowired
    public void setHttpRequestLogRepository(HttpRequestLogRepository httpRequestLogRepository) {
        this.httpRequestLogRepository = httpRequestLogRepository;
    }

	
    public HttpRequestLog save(HttpRequestLog httprequestlog) {
        return httpRequestLogRepository.save(httprequestlog);
    }

}
