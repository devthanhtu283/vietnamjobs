package com.demo.dtos;

import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.Seeker;

public class KeywordDTO {
	private Integer id;
	private int categoryid;
	private String name;
	private boolean status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public KeywordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public KeywordDTO(Integer id, int categoryid, String name, boolean status) {
		super();
		this.id = id;
		this.categoryid = categoryid;
		this.name = name;
		this.status = status;
	}
	
	
}
