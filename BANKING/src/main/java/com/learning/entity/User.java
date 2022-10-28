package com.learning.entity;

import javax.persistence.*;


@Entity
@Table(name="USERS")
public class User {
/*
 * id: Number,
 * username:String, UNIQUE 
 * fullname:String,
 * phone: String,
 * SSN: int,
 * password: String 
 * role: enum RoleType (CUSTOMER, STAFF, ADMIN)
 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="username")
	private String username;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	
	transient private String fullName = firstName + " " + lastName;
	@Column(name="phone")
	private String phone;
	@Column(name="ssn")
	private int ssn;
	@Column(name="password")
	private String password;
	
	public User() {
		super();
	}

	public User(long id, String username, String fullName, String password) {
		super();
		this.id = id;
		this.username = username;
		this.fullName = fullName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
