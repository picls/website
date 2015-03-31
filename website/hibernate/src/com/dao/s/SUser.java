package com.dao.s;

import java.util.Date;

import com.dao.Account;

public class SUser {
	private long id;
	private String name;
	private String nickname;
	private String level;
	private int article;
	private int integration;
	private String constellation;
	private Date createTime;
	private Date lastLoginTime;

	public SUser() {

	}

	public SUser(String name, String nickname, String level,
			String constellation) {
		this.name = name;
		this.nickname = nickname;
		this.level = level;
		this.article = 0;
		this.integration = 0;
		this.constellation = constellation;
	}

	public SUser(String name, String nickname, String level,
			String constellation, int article, int integration) {
		this.name = name;
		this.nickname = nickname;
		this.level = level;
		this.article = article;
		this.integration = integration;
		this.constellation = constellation;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getArticle() {
		return article;
	}

	public void setArticle(int article) {
		this.article = article;
	}

	public int getIntegration() {
		return integration;
	}

	public void setIntegration(int integration) {
		this.integration = integration;
	}

	public String getConstellation() {
		return constellation;
	}

	public void setConstellation() {
		this.constellation = constellation;
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

	public String toString() {
		String back = null;
		back = name + " " + nickname + " " + level + " " + article + " "
				+ integration + " " + constellation;
		return back;
	}

	public String toString(String delimiter) {
		String back = null;
		back = name + delimiter + nickname + delimiter + level + delimiter
				+ article + delimiter + integration + delimiter + constellation;
		return back;
	}
}
