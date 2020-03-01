package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName="httprequestlogindex",type="httprequestlog",shards=2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpRequestLog {

	@Id
	private String id;
	private String host;
	private String user_agent;
	private String accept;
	private String accept_language;
	private String accept_encoding;
	private String connection;
	private String cookie;
	private String upgrade_insecure_requests;
}
