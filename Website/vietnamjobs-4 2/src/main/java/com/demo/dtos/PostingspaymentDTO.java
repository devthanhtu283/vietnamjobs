package com.demo.dtos;

import java.util.Date;

import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.fasterxml.jackson.annotation.JsonFormat;

public class PostingspaymentDTO {
	private Integer id;
	private int postingsid;
	private double cost;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	private boolean status;
	private int duration;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getPostingsid() {
		return postingsid;
	}
	public void setPostingsid(int postingsid) {
		this.postingsid = postingsid;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public PostingspaymentDTO(Integer id, int postingsid, double cost, Date created, boolean status, int duration) {
		super();
		this.id = id;
		this.postingsid = postingsid;
		this.cost = cost;
		this.created = created;
		this.status = status;
		this.duration = duration;
	}
	public PostingspaymentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
}
