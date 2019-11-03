package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "emp")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	public Employee(String name) {
		super();
		this.name = name;
	}

	@Column(name = "name")
	private String name;

	@Column(name = "company")
	private String company;
	
	@Column(name = "dept")
	private String dept;

	@Column(name = "gender")
	private String gender;

	public Employee() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employee(String name, String company, String dept, String gender) {
		super();
		this.name = name;
		this.company = company;
		this.dept = dept;
		this.gender = gender;
	}

	public Employee(long id, String name, String company, String dept, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.dept = dept;
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", company=" + company + ", dept=" + dept + ", gender="
				+ gender + "]";
	}

}