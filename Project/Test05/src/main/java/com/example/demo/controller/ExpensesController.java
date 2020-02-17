package com.example.demo.controller;

import java.io.FileReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.CreditScore;
import com.example.demo.model.Expenses;
import com.example.demo.model.Users;
import com.example.demo.services.CreditScoreService;
import com.example.demo.services.ExpensesService;
import com.example.demo.services.UsersService;
import com.sun.research.ws.wadl.Link;


@Controller
public class ExpensesController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ExpensesService expensesService;
	
	@Autowired
	private CreditScoreService creditScoreService;
	
	@RequestMapping("users")
	public String users(HttpServletRequest req) {
//		HttpSession session =req.getSession();
	//	String username = req.getParameter("username");
		//session.setAttribute("username", username);
		//ModelAndView mv = new ModelAndView();
		
		return "users";
	}
	
	@GetMapping("error404")
	public String showErrorPage() {
		
		return "error404";
	}
	
	@GetMapping("show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users2", usersService.showAllUsers());
		
		return "users";
	}
	@GetMapping("expenses/{userId}")
	public String getExpenses(HttpServletRequest request,@PathVariable("userId")Integer userId) {
		request.setAttribute("expenses2", expensesService.showSpecificUserExpenses(userId));
		request.setAttribute("userName", usersService.getUserById(userId).getName_user());
		double total=0;
		for(Expenses objexp :expensesService.showSpecificUserExpenses(userId)) {
			int id=objexp.getId_user();
			if (id==userId) {
				if (objexp.getType().equals("C")) {
					total= total-objexp.getValue();
				}else {
					total= total+objexp.getValue();
				}
			}			
			System.out.println(""+objexp.getDescription());
		}
		String strletter="";
		for(CreditScore objcre :creditScoreService.showAllCreditScore()) {
			if (objcre.getMin_value()<=total && objcre.getMax_value()>=total) {
				strletter=objcre.getIdentify_letter();
				break;
			}	
		}
		request.setAttribute("letterCreditScore", strletter);
		
		
		request.setAttribute("total", total);
		return "expenses";
	}
	@RequestMapping(value ="link/{string}", method = RequestMethod.GET)
	public ModelAndView showUser(HttpServletRequest request,@PathVariable String string) {
		//HttpSession session =request.getSession();
		//String username = request.getParameter("username");
		//request.setAttribute("users2", usersService.showSpecificUser(id));
		String projectUrl="";
		if (string.equals("users")) {
			projectUrl="users";
		}
		 //String projectUrl = "yahoo.com";
		//httpServletResponse.setHeader("Location", projectUrl);
	    //httpServletResponse.setStatus(302);
		//String projectUrl = "users";
	    return new ModelAndView("redirect:" + projectUrl);
	}
	@RequestMapping(value = "/redirect", method = RequestMethod.GET)
	public ModelAndView method() {
		 String projectUrl = "users";
	    return new ModelAndView("redirect:" + projectUrl);
	}
	/**
	 * 
	 * @param id
	 * @return
	 
	@GetMapping("/show-users/{id}")
	public String findOne(@PathVariable Integer id) {
		System.out.println(""+ id);
		return "users";
	}*/
	
	@RequestMapping("/test")
	public String test() {
//		HttpSession session =req.getSession();
	//	String username = req.getParameter("username");
		//session.setAttribute("username", username);
		//ModelAndView mv = new ModelAndView();
		System.out.println("Hello");
		return "test";
	}
	/**
	 * 
	 * @param strElementDB
	 * @return
	 * @throws Exception
	 
	@GetMapping("/link/{str}")
	public URL getLinkToFile(@PathVariable String strElementDB) throws Exception {
	    URL base = new URL("https://file-examples.com/wp-content/uploads/2017/02/file_example_CSV_5000.csv");
	    return base;
	}*/
}
