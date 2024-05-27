package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.dtos.AdminDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Category;
import com.demo.entities.TypeAccount;

public interface CategoryService {
	
	public boolean save(Category item);

	public Category findbyname(String name);

	public Category findbyparentcategoryid(int id);

	public Iterable<Category> getAll();

	public Iterable<Category> getParent();

	public Iterable<Category> getChildren(int id);

	public boolean delete(int id);

	public boolean exists(String name, int id);

	public Category find(int id);


	public List<Object[]> getCountPostingsByCategory();
	
	public List<Category> findAllByStatus(Boolean status);

	public boolean hasChildren(int id);

}
