package com.dao;

import java.util.Date;

public class Article {
	private long id;
	private String board;
	private String title;
	private long creator;
	private int number;
	private Date createTime;
	private Date lastChangeTime;

	public Article() {

	}

	public Article(String board, String title, long creator) {
		this.board = board;
		this.title = title;
		this.creator = creator;
		this.number = 1;
		this.createTime = new Date();
		this.lastChangeTime = new Date();
	}

	public Article(String board, String title, long creator, int number) {
		this.board = board;
		this.title = title;
		this.creator = creator;
		this.number = number;
		this.createTime = new Date();
		this.lastChangeTime = new Date();
	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastChangeTime() {
		return lastChangeTime;
	}

	public void setLastChangeTime(Date lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}
}
