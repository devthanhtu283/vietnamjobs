package com.demo.dtos;

import java.util.Date;

import com.demo.entities.Account;

public class TransactionHistoryDTO {
	private Integer id;
	private int accountid;
	private double total;
	private Date created;
	private int tradingCode;
	private boolean status;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getAccountid() {
		return accountid;
	}
	public void setAccountid(int accountid) {
		this.accountid = accountid;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getTradingCode() {
		return tradingCode;
	}
	public void setTradingCode(int tradingCode) {
		this.tradingCode = tradingCode;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public TransactionHistoryDTO(Integer id, int accountid, double total, Date created, int tradingCode,
			boolean status) {
		super();
		this.id = id;
		this.accountid = accountid;
		this.total = total;
		this.created = created;
		this.tradingCode = tradingCode;
		this.status = status;
	}
	public TransactionHistoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
