package com.demo.dtos;

import java.util.Date;

import com.demo.entities.Postings;
import com.demo.entities.Seeker;


public class CheckNewDTO {
	private Integer id;
	private int postingsid;
	private int seekerid;
	private boolean status;
	
	public CheckNewDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CheckNewDTO(Integer id, int postingsid, int seekerid, boolean status) {
		super();
		this.id = id;
		this.postingsid = postingsid;
		this.seekerid = seekerid;
		this.status = status;
	}
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
	public int getSeekerid() {
		return seekerid;
	}
	public void setSeekerid(int seekerid) {
		this.seekerid = seekerid;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
