package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.CreditScore;
import com.example.demo.model.Expenses;
import com.example.demo.model.Users;

public interface CreditScoreDao extends CrudRepository<CreditScore, Integer> {
	
	
}
