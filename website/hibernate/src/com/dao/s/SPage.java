package com.dao.s;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SPage {
	private long id;
	private long boardId;
	private String url;
	private String title;
	private int pageNum;
	private Date createTime;
	private int replyNum;
	private long creator;
	private SUser suser;
	private Set sposts = new HashSet();

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getBoardId() {
		return boardId;
	}

	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public SUser getSuser() {
		return suser;
	}

	public void setSuser(SUser suser) {
		this.suser = suser;
	}

	public Set getSposts() {
		return sposts;
	}

	public void setSposts(Set sposts) {
		this.sposts = sposts;
	}

	public String toString() {
		String back = boardId + " " + url + " " + title + " " + pageNum + " "
				+ createTime.toString() + " " + replyNum + " " + creator;
		return back;
	}

	public String toString(String delimiter) {
		String back = boardId + delimiter + url + delimiter + title + delimiter
				+ pageNum + delimiter + createTime.toString() + delimiter
				+ replyNum + delimiter + creator;
		return back;
	}

}
