package com.dao;

import java.util.Date;

public class Account {
	private long id;
	private String name;
	private String password;
	private String email;
	private long qq;
	private Date createTime;
	private Date lastLoginTime;
	private User user;

	public Account() {

	}

	public Account(String name, String password, String email, long qq) {
		this.name = name;
		this.password = password;
		this.email = email;
		this.qq = qq;
		this.createTime = new Date();
		this.lastLoginTime = new Date();
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getQq() {
		return qq;
	}

	public void setQq(long qq) {
		this.qq = qq;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String toString() {
		String back = null;
		back = name + " " + password + " " + email + " " + qq + " "
				+ createTime.toString() + " " + lastLoginTime.toString();
		return back;
	}

	public String toString(String delimiter) {
		String back = null;
		back = name + delimiter + password + delimiter + email + delimiter + qq
				+ delimiter + createTime.toString() + delimiter
				+ lastLoginTime.toString();
		return back;
	}
}
