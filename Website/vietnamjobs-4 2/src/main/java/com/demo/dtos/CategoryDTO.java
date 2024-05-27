package com.demo.dtos;

public class CategoryDTO {
	private Integer id;
	private int categoryId;
	private String name;
	private boolean status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoryDTO(Integer id, int categoryId, String name, boolean status) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.name = name;
		this.status = status;
	}
	
	
}
