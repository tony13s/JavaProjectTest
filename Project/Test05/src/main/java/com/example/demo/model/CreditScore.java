package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="credit_score")
public class CreditScore {

@Id
@Column(name="id_credit")
private int id_credit;

@Column(name="min_value")
private double min_value;

@Column(name="max_value")
private double max_value;

@Column(name="identify_letter")
private String identify_letter;

public CreditScore() {}

public CreditScore(int id_credit, double min_value, double max_value, String identify_letter) {
	super();
	this.id_credit = id_credit;
	this.min_value = min_value;
	this.max_value = max_value;
	this.identify_letter = identify_letter;
}

public int getId_credit() {
	return id_credit;
}

public void setId_credit(int id_credit) {
	this.id_credit = id_credit;
}

public double getMin_value() {
	return min_value;
}

public void setMin_value(double min_value) {
	this.min_value = min_value;
}

public double getMax_value() {
	return max_value;
}

public void setMax_value(double max_value) {
	this.max_value = max_value;
}

public String getIdentify_letter() {
	return identify_letter;
}

public void setIdentify_letter(String identify_letter) {
	this.identify_letter = identify_letter;
}

}
