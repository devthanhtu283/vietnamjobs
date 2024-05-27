package com.demo.services;

import com.demo.entities.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repositories.CategoryRepository;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category findbyname(String name) {
		return categoryRepository.findbyname(name);
	}

	@Override
	public Category findbyparentcategoryid(int id) {
		return categoryRepository.findbycategoryParentId(id);
	}

	@Override
	public Iterable<Category> getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public Iterable<Category> getParent() {
		return categoryRepository.getParent();
	}

	@Override
	public Iterable<Category> getChildren(int id) {
		return categoryRepository.getChildren(id);
	}

	@Override
	public boolean save(Category local) {
		try {
			categoryRepository.save(local);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		try {
			categoryRepository.delete(categoryRepository.findById(id).get());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean exists(String name, int id) {
		return categoryRepository.exists(name, id) > 0;
	}

	@Override
	public Category find(int id) {
		return categoryRepository.findById(id).get();
	}
	
	@Override
	public List<Object[]> getCountPostingsByCategory() {
        return categoryRepository.countPostingsByCategory();
    }
	
	@Override
	public List<Category> findAllByStatus(Boolean status) {
		return categoryRepository.findAllByStatus(status);
	}

	public boolean hasChildren(int id) {
		return categoryRepository.hasChildren( id) > 0;
	}

}
