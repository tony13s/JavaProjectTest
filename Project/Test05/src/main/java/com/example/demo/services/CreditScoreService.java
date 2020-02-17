package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.CreditScoreDao;
import com.example.demo.dao.ExpensesDao;
import com.example.demo.model.CreditScore;
import com.example.demo.model.Expenses;
@Service
@Transactional
public class CreditScoreService {
	
	@Autowired
	private final CreditScoreDao creditScoreDao;
	
	public CreditScoreService(CreditScoreDao creditScoreDao) {
		this.creditScoreDao=creditScoreDao;
	}
	
	
	
	public List<CreditScore> showAllCreditScore(){
		List<CreditScore> creditScore = new ArrayList<CreditScore>();		
		for(CreditScore objCreditScore :creditScoreDao.findAll()) {
			creditScore.add(objCreditScore);
		//	System.out.println(""+user.getName_user());
		}
		
		return creditScore;
	}	
}
