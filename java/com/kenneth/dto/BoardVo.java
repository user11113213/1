package com.kenneth.dto;

import java.sql.Date;

public class BoardVo {
	private int code;
	private String name;
	private String title;
	private String pictureurl;
	private String description;
	private Date reg_date;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "BoardVo [code=" + code + ", name=" + name + ", title=" + title + ", pictureurl=" + pictureurl
				+ ", description=" + description + ", reg_date=" + reg_date + "]";
	}
	
	
	
	
	
}
