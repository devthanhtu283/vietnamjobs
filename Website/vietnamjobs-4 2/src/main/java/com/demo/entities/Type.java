package com.demo.entities;
// Generated May 5, 2024, 7:24:16 PM by Hibernate Tools 4.3.6.Final

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;

/**
 * Type generated by hbm2java
 */
@Entity
@Table(name = "type", catalog = "vietnamjobs")
public class Type implements java.io.Serializable {

	private Integer id;
	private String name;
	private boolean status;
	private Set<Postings> postingses = new HashSet<Postings>(0);

	public Type() {
	}

	public Type(String name, boolean status) {
		this.name = name;
		this.status = status;
	}

	public Type(String name, boolean status, Set<Postings> postingses) {
		this.name = name;
		this.status = status;
		this.postingses = postingses;
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

	@Column(name = "status", nullable = false)
	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "type")
	public Set<Postings> getPostingses() {
		return this.postingses;
	}

	public void setPostingses(Set<Postings> postingses) {
		this.postingses = postingses;
	}

}