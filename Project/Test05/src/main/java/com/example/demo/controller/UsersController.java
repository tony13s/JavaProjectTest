package com.example.demo.controller;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.CustomRequestLogginFilter;
import com.example.demo.model.HttpRequestLog;
import com.example.demo.model.Users;
import com.example.demo.repository.HttpRequestLogRepository;
import com.example.demo.services.HttpRequestLogService;
import com.example.demo.services.UsersService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.annotations.Expose;
import com.google.gson.stream.JsonWriter;
import com.sun.research.ws.wadl.Link;


@RestController
@RequestMapping(value="link")
public class UsersController {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
    private HttpRequestLogService httprequestlogService;
	
	@Autowired
	private HttpRequestLogRepository repository;
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping(value="/createuser")
	public Users createUser(Users user) {
		return usersService.createUser(user);
	}
	@GetMapping("/findAll")
	public Iterable<HttpRequestLog> findAllHttpRequestLog() {
		return repository.findAll();
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
			
			System.out.println(request.getHeaderNames());
			Enumeration<String> enumHeaders =request.getHeaderNames();
			
			HttpRequestLog objht = new HttpRequestLog();
			while(enumHeaders.hasMoreElements()==true) {
				String strHeader=enumHeaders.nextElement();
				System.out.println(strHeader);
				System.out.println(request.getHeader(""+strHeader));
				if (strHeader.contains("host")) {
					objht.setHost(request.getHeader(""+strHeader));
				}else if (strHeader.contains("user-agent")) {
					objht.setUser_agent(request.getHeader(""+strHeader));
				}else if (strHeader.contains("accept")) {
					objht.setAccept(request.getHeader(""+strHeader));
				}else if (strHeader.contains("accept-language")) {
					objht.setAccept_language(request.getHeader(""+strHeader));
				}else if (strHeader.contains("accept-encoding")) {
					objht.setAccept_encoding(request.getHeader(""+strHeader));
				}else if (strHeader.contains("connection")) {
					objht.setConnection(request.getHeader(""+strHeader));
				}else if (strHeader.contains("cookie")) {
					objht.setCookie(request.getHeader(""+strHeader));
				}else if (strHeader.contains("upgrade-insecure-requests")) {
					objht.setUpgrade_insecure_requests(request.getHeader(""+strHeader));
				}
				
			}
			Gson gsonBuilder = new GsonBuilder().create();
			String jsonFromPojo = gsonBuilder.toJson(objht);
			System.out.println(jsonFromPojo);
			//httprequestlogService.save(objht);
			createFile(objht);
			//httprequestlogService.save(new HttpRequestLog("1", "Ped", "Moo", 26));
			
			JSONObject jsonHttp = new JSONObject();
		   JSONObject jsonHead = new JSONObject();
		   JSONArray  jsonAll = new JSONArray();
		   
		try{
			   FileInputStream fstream = new FileInputStream("C:/myapp.log");
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
			   LOGGER.debug("TONY SANGINEZ ESTO ES UNA PRUEBA DE SIMULACRO");
			} catch (Exception e) {
			     System.err.println("Error: " + e.getMessage());
			}
	     
	     
	    //Add employees to list
	   
		
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
private void createFile(HttpRequestLog http) {
	//Write JSON file
	/**
	 * 
	 
    try (FileWriter file = new FileWriter("MyAppLogs/click.json",true)) {
        file.write(strJSON);
        file.flush();

    } catch (IOException e) {
        e.printStackTrace();
    }*/
    
    
    try {
        String nameRead;
        JsonParser parser = new JsonParser();
        Object obj = parser.parse(new FileReader("MyAppLogs/click.json"));
        JsonObject jsonObject = (JsonObject) obj;
        JsonArray msg = (JsonArray)jsonObject.get("cab");
        Iterator<JsonElement> iterator = msg.iterator();
        while(iterator.hasNext()) {
           nameRead = iterator.next().toString();
           System.out.println(nameRead);
        }
        HttpRequestLog httpLog = http;
        Gson gson = new Gson();
        String json = gson.toJson(httpLog);

        FileWriter file = new FileWriter("MyAppLogs/click.json", false);
        JsonWriter jw = new JsonWriter(file);
        iterator = msg.iterator();
        Cab cabs = new Cab();
        while(iterator.hasNext()) {
           cabs.addEmployee(gson.fromJson(iterator.next().toString(), HttpRequestLog.class));
        }
        cabs.addEmployee(httpLog);
        gson.toJson(cabs, Cab.class, jw);
        file.close();
     } catch(Exception e) {
        e.printStackTrace();
     }
	
	/**
	 * 
	 
	BufferedWriter bw = null;
    FileWriter fw = null;

    try {
        String data = strJSON;
        File file = new File("MyAppLogs/click.json");
        // Si el archivo no existe, se crea!
        if (!file.exists()) {
            file.createNewFile();
        }
        // flag true, indica adjuntar información al archivo.
        fw = new FileWriter(file.getAbsoluteFile(), true);
        bw = new BufferedWriter(fw);
        bw.write(data);
        //System.out.println("información agregada!");
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
                        //Cierra instancias de FileWriter y BufferedWriter
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
	*/
}
//cab class
class Cab {
@Expose
List<HttpRequestLog> cab = new ArrayList<>();
public List<HttpRequestLog> getEmployees() {
   return cab;
}
public void addEmployee(HttpRequestLog httpLog) {
   this.cab.add(httpLog);
}
}

}
