package com.demo.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostingDTO {
	private int id;
	private String employerName;
	private String employerLogo;
	private String title;
	private String description;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date created;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dealine;
	private String gender;
	private int quantity;
	private String wageName;
	private String categoryName;
	private String localName;
	private String rankName;
	private String typeName;
	private String expName;
	private boolean status;
	
	
	public String getWageName() {
		return wageName;
	}
	public void setWageName(String wageName) {
		this.wageName = wageName;
	}
	public PostingDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PostingDTO(int id, String employerName, String employerLogo, String title, String description, Date created,
			Date dealine, String gender, int quantity, String wageName, String categoryName, String localName,
			String rankName, String typeName, String expName, boolean status) {
		super();
		this.id = id;
		this.employerName = employerName;
		this.employerLogo = employerLogo;
		this.title = title;
		this.description = description;
		this.created = created;
		this.dealine = dealine;
		this.gender = gender;
		this.quantity = quantity;
		this.wageName = wageName;
		this.categoryName = categoryName;
		this.localName = localName;
		this.rankName = rankName;
		this.typeName = typeName;
		this.expName = expName;
		this.status = status;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmployerLogo() {
		return employerLogo;
	}
	public void setEmployerLogo(String logo) {
		this.employerLogo = logo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getDealine() {
		return dealine;
	}
	public void setDealine(Date dealine) {
		this.dealine = dealine;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getRankName() {
		return rankName;
	}
	public void setRankName(String rankName) {
		this.rankName = rankName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getExpName() {
		return expName;
	}
	public void setExpName(String expName) {
		this.expName = expName;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PostingDTO [id=" + id + ", employerName=" + employerName + ", employerLogo=" + employerLogo + ", title="
				+ title + ", description=" + description + ", created=" + created + ", dealine=" + dealine + ", gender="
				+ gender + ", quantity=" + quantity + ", wageName=" + wageName + ", categoryName=" + categoryName
				+ ", localName=" + localName + ", rankName=" + rankName + ", typeName=" + typeName + ", expName="
				+ expName + ", status=" + status + "]";
	}
	
}
