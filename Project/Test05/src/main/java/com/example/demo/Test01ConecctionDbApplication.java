package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.demo.services.CreditScoreService;
import com.example.demo.services.ExpensesService;
import com.example.demo.services.UsersService;

@SpringBootApplication
@Import({UsersService.class,ExpensesService.class,CreditScoreService.class})
@EnableScheduling
public class Test01ConecctionDbApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext=SpringApplication.run(Test01ConecctionDbApplication.class, args);
		
	}

}
