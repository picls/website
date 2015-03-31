package com.dao;

import java.util.Date;

public class Post {
	private long article;
	private String board;
	private String title;
	private long creator;
	private int number;
	private Date createTime;
	private Date lastChangeTime;


	public long getArticle() {
		return article;
	}

	private void setArticle(long id) {
		this.article = article;
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
