package com.dao;

public class User {
	private long id;
	private String name;
	private String nickname;
	private String description;
	private int passageNumber;
	private int integration;
	private Account account;

	public User() {

	}

	public User(String name, String nickname, String description) {
		this.name = name;
		this.nickname = nickname;
		this.description = description;
		this.passageNumber = 0;
		this.integration = 0;
	}

	public User(String name, String nickname, String description,
			int passageNumber, int integration) {
		this.name = name;
		this.nickname = nickname;
		this.description = description;
		this.passageNumber = passageNumber;
		this.integration = integration;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPassageNumber() {
		return passageNumber;
	}

	public void setPassageNumber(int passageNumber) {
		this.passageNumber = passageNumber;
	}

	public int getIntegration() {
		return integration;
	}

	public void setIntegration(int integration) {
		this.integration = integration;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String toString() {
		String back = null;
		back = name + " " + nickname + " " + description + " " + passageNumber
				+ " " + integration;
		return back;
	}

	public String toString(String delimiter) {
		String back = null;
		back = name + delimiter + nickname + delimiter + description
				+ delimiter + passageNumber + delimiter + integration;
		return back;
	}
}
