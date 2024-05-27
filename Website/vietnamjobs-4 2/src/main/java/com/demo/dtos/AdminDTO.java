package com.demo.dtos;

public class AdminDTO {
	private Integer id;
	private int accountId;
	private String accountName;
	private String fullname;
	private String phone;
	private String photo;
	
	public AdminDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminDTO(Integer id, int accountId, String accountName, String fullname, String phone, String photo) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.fullname = fullname;
		this.phone = phone;
		this.photo = photo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
