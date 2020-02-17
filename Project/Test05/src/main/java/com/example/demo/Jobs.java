package com.example.demo;


import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Jobs {
	private static final Logger log = LoggerFactory.getLogger(Jobs.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(cron = "0 5 * * * ?")
	public void reportCurrentTime() {
		log.info("Cron Task :: Execution Time - {}", dateFormat.format(new Date()));
		 try {
			 JSONParser parser = new JSONParser();
		         
			 JSONArray jsonArray = (JSONArray) parser.parse(new FileReader("MyAppLogs/click.json"));

		        for (Object object : jsonArray)
		        {
		          JSONObject config = (JSONObject) object;
		          JSONObject httpHeader = (JSONObject) config.get("HttpRequest");
		          String verb = (String) httpHeader.get("headers");
		          //String regex = "(?i)user-agent:+([\\S]+)\\s+([\\S]+)";
		          String remainingPart= verb.substring(verb.indexOf("user-agent"));
		          String remainingPart2= verb.substring(verb.indexOf("("));
		          System.out.println("Operative System"+remainingPart2);
		          System.out.println("Operative System Version"+remainingPart2);
		          System.out.println("Browser"+remainingPart);
		          System.out.println("Browser Version"+verb);
		        }
		       
		    } catch (Exception e) {
		        System.out.println("Exception " + e);
		    }
	
	}
}
