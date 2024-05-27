package com.demo.dtos;

import java.util.Date;

public class ApplicationHistoryDTO {
	private int id;
	private int postingID;
	private String postingTitle;
	private String employerName;
	private Date postingCreated;
	private Date postingDeadline;
	private int seekerID;
	private String seekerName;
	private String seekerCV;
	private int status;
	private int result;
	private String seekerEmail;
	private Date create;
	
	public int getPostingID() {
		return postingID;
	}
	public void setPostingID(int postingID) {
		this.postingID = postingID;
	}
	public int getSeekerID() {
		return seekerID;
	}
	public void setSeekerID(int seekerID) {
		this.seekerID = seekerID;
	}
	public ApplicationHistoryDTO(int id, String postingTitle, String employerName, Date postingCreated,
			Date postingDeadline, String seekerName, String seekerCV, int status, int result, Date create) {
		super();
		this.id = id;
		this.postingTitle = postingTitle;
		this.employerName = employerName;
		this.postingCreated = postingCreated;
		this.postingDeadline = postingDeadline;
		this.seekerName = seekerName;
		this.seekerCV = seekerCV;
		this.status = status;
		this.result = result;
		this.create = create;
	}
	public ApplicationHistoryDTO() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPostingTitle() {
		return postingTitle;
	}
	public void setPostingTitle(String postingTitle) {
		this.postingTitle = postingTitle;
	}
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	public Date getPostingCreated() {
		return postingCreated;
	}
	public void setPostingCreated(Date postingCreated) {
		this.postingCreated = postingCreated;
	}
	public Date getPostingDeadline() {
		return postingDeadline;
	}
	public void setPostingDeadline(Date postingDeadline) {
		this.postingDeadline = postingDeadline;
	}
	public String getSeekerName() {
		return seekerName;
	}
	public void setSeekerName(String seekerName) {
		this.seekerName = seekerName;
	}
	public String getSeekerCV() {
		return seekerCV;
	}
	public void setSeekerCV(String seekerCV) {
		this.seekerCV = seekerCV;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getCreate() {
		return create;
	}
	public void setCreate(Date create) {
		this.create = create;
	}
	
	public String getSeekerEmail() {
		return seekerEmail;
	}
	public void setSeekerEmail(String seekerEmail) {
		this.seekerEmail = seekerEmail;
	}
	@Override
	public String toString() {
		return "ApplicationHistoryDTO [id=" + id + ", postingID=" + postingID + ", postingTitle=" + postingTitle
				+ ", employerName=" + employerName + ", postingCreated=" + postingCreated + ", postingDeadline="
				+ postingDeadline + ", seekerID=" + seekerID + ", seekerName=" + seekerName + ", seekerCV=" + seekerCV
				+ ", status=" + status + ", result=" + result + ", create=" + create + "]";
	}
	
	
}
