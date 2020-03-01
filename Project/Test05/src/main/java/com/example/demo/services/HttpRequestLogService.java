package com.example.demo.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.demo.model.HttpRequestLog;


public interface HttpRequestLogService {
	HttpRequestLog save(HttpRequestLog httprequestlog);

}
