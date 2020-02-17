package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.UsersDao;
import com.example.demo.model.Users;
@Service
@Transactional
public class UsersService {
	
	@Autowired
	private final UsersDao usersDao;
	
	public UsersService(UsersDao usersDao) {
		this.usersDao=usersDao;
	}
	
	public Users createUser(Users user) {
		
		return usersDao.save(user);
		
	}
	
	public List<Users> showAllUsers(){
		List<Users> users = new ArrayList<Users>();
		
		for(Users user :usersDao.findAll()) {
			users.add(user);
		//	System.out.println(""+user.getName_user());
		}
		
		return users;
	}
	public List<Users> showSpecificUser(int id_user){
		List<Users> users = new ArrayList<Users>();
		//Users user= usersDao.findById(id_user);
		for(Users user :usersDao.findAll()) {
			int id=user.getId_user();
			if (id==id_user) {
				users.add(user);	
			}			
			System.out.println(""+user.getName_user());
		}
		
		return users;
	}

	public Users getUserById(Integer userId) {
		Optional<Users> user = usersDao.findById(userId);
		return user.get();
		}
	
}
