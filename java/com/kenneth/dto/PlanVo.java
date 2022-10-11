package com.kenneth.dto;

import java.sql.Date;

public class PlanVo {
	private int code;
	private String name;
	private String title;
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
		return "PlanVo [code=" + code + ", name=" + name + ", title=" + title + ", description=" + description
				+ ", reg_date=" + reg_date + "]";
	}
	
	
	
}
