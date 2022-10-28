package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PASSWORDS")
public class Password {
	/*
	 * userId
	 * salt
	 * hash
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@Column(name="salt")
	private String salt;
	@Column(name="hash")
	private long hash;
	
	public Password() {
		super();
	}

	public Password(long id, String salt, long hash) {
		super();
		this.id = id;
		this.salt = salt;
		this.hash = hash;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public long getHash() {
		return hash;
	}

	public void setHash(long hash) {
		this.hash = hash;
	}

}
