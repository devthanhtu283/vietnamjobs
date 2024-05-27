package com.demo.dtos;

public class SeekerDTO {
	private int id;
	private int accountID;
	private String accountName;
	private String fullName;
	private String phone;
	private String avatar;
	private String description;
	private String cvInformation;
	private boolean status;

	public SeekerDTO(int id, int accountID, String accountName, String fullName, String phone, String avatar,
			String description, String cvInformation, boolean status) {
		super();
		this.id = id;
		this.accountID = accountID;
		this.accountName = accountName;
		this.fullName = fullName;
		this.phone = phone;
		this.avatar = avatar;
		this.description = description;
		this.cvInformation = cvInformation;
		this.status = status;
	}
	public SeekerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCvInformation() {
		return cvInformation;
	}
	public void setCvInformation(String cvInformation) {
		this.cvInformation = cvInformation;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "SeekerDTO [id=" + id + ", accountID=" + accountID + ", accountName=" + accountName + ", fullName="
				+ fullName + ", phone=" + phone + ", avatar=" + avatar + ", description=" + description
				+ ", cvInformation=" + cvInformation + ", status=" + status + "]";
	}
	
	
}
