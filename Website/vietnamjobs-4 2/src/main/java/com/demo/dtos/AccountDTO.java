package com.demo.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class AccountDTO {
	private Integer id;
	private String typeAccount;
	private int typeAccountID;
	private String username;
	private String password;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	private String email;
	private boolean status;
	private String securityCode;
	private double wallet;
	public AccountDTO(Integer id, String typeAccount, int typeAccountID, String username, String password, Date created,
			String email, boolean status, String securityCode, double wallet) {
		super();
		this.id = id;
		this.typeAccount = typeAccount;
		this.typeAccountID = typeAccountID;
		this.username = username;
		this.password = password;
		this.created = created;
		this.email = email;
		this.status = status;
		this.securityCode = securityCode;
		this.wallet = wallet;
	}
	public AccountDTO() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	public int getTypeAccountID() {
		return typeAccountID;
	}
	public void setTypeAccountID(int typeAccountID) {
		this.typeAccountID = typeAccountID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getSecurityCode() {
		return securityCode;
	}
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	public double getWallet() {
		return wallet;
	}
	public void setWallet(double wallet) {
		this.wallet = wallet;
	}
	
	
}
