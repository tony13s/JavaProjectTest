package com.example.demo.controller;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Users;
import com.example.demo.services.UsersService;
import com.sun.research.ws.wadl.Link;


@RestController
@RequestMapping(value="link")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping(value="/createuser")
	public Users createUser(Users user) {
		return usersService.createUser(user);
	}
	/**
	 * 
	 * @param userId
	 * @return
	 
	@GetMapping(value="/users/{userId}")
	public Users getUserById(@PathVariable("userId")Integer userId) {
		return usersService.getUserById(userId);
	}
	*/
	@GetMapping(value="/users/{userId}")
	public ModelAndView getUser(@PathVariable("userId")Integer userId,
			@CookieValue(value = "cookie", defaultValue = "0") Long hitCounter,
			HttpServletResponse response,HttpServletRequest request) {
		JSONObject jsonHttp = new JSONObject();
		   JSONObject jsonHead = new JSONObject();
		   JSONArray  jsonAll = new JSONArray();
		   
		try{
			   FileInputStream fstream = new FileInputStream("MyAppLogs/myapp.log");
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   String regex = "(?i)GET\\s+([\\S]+)\\s+([\\S]+)";
			   String regex2 = "(?i)headers=";
			  
			   /* read log line by line */
			   while ((strLine = br.readLine()) != null)   {
				   if (strLine.contains("o.s.w.f.CommonsRequestLoggingFilter      : Before request [GET /")) {
		//			     System.out.println ("PRUEBAAAAAAAAAAAAAAAAA DE CARGA"+strLine);
					     Matcher m = Pattern.compile(String.format(regex, Pattern.quote("GET"))).matcher(strLine);
					     while (m.find())
						   {
						       jsonHttp.put("GET", m.group(1));
						         jsonHttp.put("client", m.group(2));
						 	       
						   }
					     String remainingPart= strLine.substring(strLine.indexOf("headers="));
					    
					     jsonHttp.put("headers", remainingPart);
					    
				}
				   jsonHead.put("HttpRequest", jsonHttp);
				   jsonAll.add(jsonHead);
			     /* parse strLine to obtain what you want */
			   }
			   
			   fstream.close();
			} catch (Exception e) {
			     System.err.println("Error: " + e.getMessage());
			}
	     
	     
	    //Add employees to list
	    
	    
	    //Write JSON file
	    try (FileWriter file = new FileWriter("MyAppLogs/click.json")) {

	        file.write(jsonAll.toJSONString());
	        file.flush();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		
		int contador=0;
		 Cookie[] cookies = request.getCookies();
		   for (Cookie cookie : cookies) {
			   if (cookie.getName().equals("cookie"+userId)) {
				  contador=Integer.valueOf(cookie.getValue());
				  break;
			}
		}
		   contador++;
		// create cookie and set it in response
		Cookie cookie = new Cookie("cookie"+userId, String.valueOf(contador));
		response.addCookie(cookie);
		System.out.println("hitcounter tony:"+userId + contador);
		Users user=usersService.getUserById(userId);
		if (user.getMax_vaue()<contador) {
			return new ModelAndView("redirect:" + "/error404");
		}
		
		return new ModelAndView("redirect:" + "/expenses"+"/"+userId);
	}
	
}
