package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.ExpensesDao;
import com.example.demo.model.Expenses;
@Service
@Transactional
public class ExpensesService {
	
	@Autowired
	private final ExpensesDao expensesDao;
	
	public ExpensesService(ExpensesDao expensesDao) {
		this.expensesDao=expensesDao;
	}
	
	
	
	public List<Expenses> showAllExpenses(){
		List<Expenses> expenses = new ArrayList<Expenses>();
		
		for(Expenses objexpenses :expensesDao.findAll()) {
			expenses.add(objexpenses);
		//	System.out.println(""+user.getName_user());
		}
		
		return expenses;
	}
	public List<Expenses> showSpecificUserExpenses(int id_user){
		List<Expenses> expenses = new ArrayList<Expenses>();
		//Users user= usersDao.findById(id_user);
		for(Expenses objexp :expensesDao.findAll()) {
			int id=objexp.getId_user();
			if (id==id_user) {
				expenses.add(objexp);	
			}			
			System.out.println(""+objexp.getDescription());
		}
		
		return expenses;
	}

	
	
}
