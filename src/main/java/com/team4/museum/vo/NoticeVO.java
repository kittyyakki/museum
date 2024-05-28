package com.team4.museum.vo;

import java.sql.Date;

public class NoticeVO {
	private Integer nseq;
	private String title;
	private String author;
	private Date writedate;
	private String content;
	private Integer readcount;
	private Integer category;

	public Integer getNseq() {
		return nseq;
	}

	public void setNseq(Integer nseq) {
		this.nseq = nseq;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getWritedate() {
		return writedate;
	}

	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

}
