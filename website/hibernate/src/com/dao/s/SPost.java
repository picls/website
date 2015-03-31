package com.dao.s;

import java.util.Date;

public class SPost {
	private long id;
	private long pageId;
	private long creator;
	private String content;
	private Date createTime;
	private String source;
	private SUser suser;
	private SPage spage;
	

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public long getCreator() {
		return creator;
	}

	public void setCreator(long creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	public SUser getSuser() {
		return suser;
	}
	
	public void setSuser(SUser suser) {
		this.suser = suser;
	}

	public SPage getSpage() {
		return spage;
	}

	public void setSpage(SPage spage) {
		this.spage = spage;
	}

	public String toString() {
		String back = pageId + " " + creator + " " + content + " "
				+ createTime.toString() + " " + source;
		return back;
	}

	public String toString(String delimiter) {
		String back = pageId + delimiter + creator + delimiter + content
				+ delimiter + createTime.toString() + delimiter + source;
		return back;
	}

}
