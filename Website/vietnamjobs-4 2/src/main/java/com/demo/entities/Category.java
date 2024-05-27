package com.demo.entities;
// Generated May 9, 2024, 7:31:00 PM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;


/**
 * Category generated by hbm2java
 */
@Entity
@Table(name = "category", catalog = "vietnamjobs")
public class Category implements java.io.Serializable {

	private Integer id;
	private String name;
	private Integer parentId;
	private boolean status;
	private Set<Postings> postingses = new HashSet<Postings>(0);
	private Set<Keyword> keywords = new HashSet<Keyword>(0);

	public Category() {
	}

	public Category(String name, boolean status) {
		this.name = name;
		this.status = status;
	}

	public Category(String name, Integer parentId, boolean status, Set<Postings> postingses, Set<Keyword> keywords) {
		this.name = name;
		this.parentId = parentId;
		this.status = status;
		this.postingses = postingses;
		this.keywords = keywords;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 250)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "parent_id")
	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Postings> getPostingses() {
		return this.postingses;
	}

	public void setPostingses(Set<Postings> postingses) {
		this.postingses = postingses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
	public Set<Keyword> getKeywords() {
		return this.keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

}
