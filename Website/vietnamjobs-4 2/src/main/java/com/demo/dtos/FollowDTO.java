package com.demo.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FollowDTO {
	private int id;
	private String employerName;
	private String seekerName;
	private boolean status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public String getSeekerName() {
		return seekerName;
	}
	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public FollowDTO(int id, String employerName, String seekerName, boolean status) {
		super();
		this.id = id;
		this.employerName = employerName;
		this.seekerName = seekerName;
		this.status = status;
	}
	public FollowDTO() {
		super();
	}
	
	
}
