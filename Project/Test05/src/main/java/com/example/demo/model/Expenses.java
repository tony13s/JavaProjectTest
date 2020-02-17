package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="expenses")
public class Expenses {

@Id
@Column(name="id_expenses")
private int id_expenses;

@Column(name="id_user")
private int id_user;

@Column(name="description")
private String description;

@Column(name="type")
private String type;

@Column(name="month")
private String month;

@Column(name="year")
private String year;

@Column(name="value")
private double value;

//@Transient
//private double total;

public Expenses() {}

public Expenses(int id_expenses, int id_user, String description, String type, String month, String year,
		double value) {
	super();
	this.id_expenses = id_expenses;
	this.id_user = id_user;
	this.description = description;
	this.type = type;
	this.month = month;
	this.year = year;
	this.value = value;
}

public int getId_expenses() {
	return id_expenses;
}

public void setId_expenses(int id_expenses) {
	this.id_expenses = id_expenses;
}

public int getId_user() {
	return id_user;
}

public void setId_user(int id_user) {
	this.id_user = id_user;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getMonth() {
	return month;
}

public void setMonth(String month) {
	this.month = month;
}

public String getYear() {
	return year;
}

public void setYear(String year) {
	this.year = year;
}

public double getValue() {
	return value;
}

public void setValue(double value) {
	this.value = value;
}



}
