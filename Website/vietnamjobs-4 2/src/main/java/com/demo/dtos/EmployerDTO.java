package com.demo.dtos;


public class EmployerDTO {
	private Integer id;
	private int accountId;
	private String accountName;
	private String name;
	private String scale;
	private String logo;
	private String link;
	private String description;
	private String address;
	private String mapLink;
	private boolean status;
	private String cover;
	private int folow;
	
	
	public EmployerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployerDTO(Integer id, int accountId, String accountName, String name, String scale, String logo,
			String link, String description, String address, String mapLink, boolean status, String cover, int folow) {
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.name = name;
		this.scale = scale;
		this.logo = logo;
		this.link = link;
		this.description = description;
		this.address = address;
		this.mapLink = mapLink;
		this.status = status;
		this.cover = cover;
		this.folow = folow;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScale() {
		return scale;
	}
	public void setScale(String scale) {
		this.scale = scale;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMapLink() {
		return mapLink;
	}
	public void setMapLink(String mapLink) {
		this.mapLink = mapLink;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public int getFolow() {
		return folow;
	}
	public void setFolow(int folow) {
		this.folow = folow;
	}
	
}
