package com.example.demo;


import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.model.HttpRequestLog;
import com.example.demo.services.HttpRequestLogService;


@Component
public class Jobs {
	private static final Logger log = LoggerFactory.getLogger(Jobs.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
    private HttpRequestLogService httprequestlogService;
	
	@Scheduled(cron = "0 5 * * * ?")
	public void reportCurrentTime() {
		log.info("Cron Task :: Execution Time - {}", dateFormat.format(new Date()));
		HttpRequestLog objht = new HttpRequestLog();
		 try {
			 JSONParser parser = new JSONParser();
		         
			 //JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("MyAppLogs/click.json"));
			 JSONObject jsonCab = (JSONObject) parser.parse(new FileReader("MyAppLogs/click.json"));
			 JSONArray jsonArray = (JSONArray) jsonCab.get("cab");
		        for (Object object : jsonArray)
		        {
		          JSONObject config = (JSONObject) object;
		          objht= new HttpRequestLog();
		          String host = (String) config.get("host");
		          objht.setHost(host);
		          
		          String user_agent = (String) config.get("user-agent");
		          objht.setUser_agent(user_agent);
		          
		          String accept = (String) config.get("accept");
		          objht.setAccept(accept);
		          
		          String accept_language = (String) config.get("accept-language");
		          objht.setAccept_language(accept_language);
		          
		          String accept_encoding = (String)config.get("accept-encoding");
		          objht.setAccept_encoding(accept_encoding);
		          
		          String connection =(String)config.get("connection");
		          objht.setConnection(connection);
		          
		          String cookie = (String) config.get("cookie");
		          objht.setCookie(cookie);
		          
		          String upgrade_insecure_requests = (String)config.get("upgrade_insecure_requests");
		          objht.setUpgrade_insecure_requests(upgrade_insecure_requests);
		          
		          httprequestlogService.save(objht);
		          
		          //String regex = "(?i)user-agent:+([\\S]+)\\s+([\\S]+)";
		          //String remainingPart= verb.substring(verb.indexOf("user-agent"));
		          //String remainingPart2= verb.substring(verb.indexOf("("));
		          //System.out.println("Operative System"+remainingPart2);
		          //System.out.println("Operative System Version"+remainingPart2);
		          //System.out.println("Browser"+remainingPart);
		          //System.out.println("Browser Version"+verb);
		        }
		       
		    } catch (Exception e) {
		        System.out.println("Exception " + e);
		    }
	
	}
}
