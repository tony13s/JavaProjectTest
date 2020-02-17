package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class Users {

@Id
@Column(name="id_user")
private int id_user;

@Column(name="name_user")
private String name_user;

@Column(name="max_value")
private int max_value;

public Users() {
	
}
public Users(int id_user, String name_user,int max_value) {
	super();
	this.id_user = id_user;
	this.name_user = name_user;
	this.max_value=max_value;
}
public int getId_user() {
	return id_user;
}
public void setId_user(int id_user) {
	this.id_user = id_user;
}
public String getName_user() {
	return name_user;
}
public void setName_user(String name_user) {
	this.name_user = name_user;
}
public int getMax_vaue() {
	return max_value;
}
public void setMax_value(int max_value) {
	this.max_value = max_value;
}

}
