package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Expenses;
import com.example.demo.model.Users;

public interface ExpensesDao extends CrudRepository<Expenses, Integer> {
	
	
}
