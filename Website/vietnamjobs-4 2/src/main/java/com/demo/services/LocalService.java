package com.demo.services;

import com.demo.entities.Local;

import java.util.List;


public interface LocalService {
	
	public Local find(int id);

	public Iterable<Local> findAll();

	public boolean save(Local local);

	public boolean delete(int id);

	public boolean exists(String name, int id);

//	public boolean existsPost(int id);

	public List<Local> findAllByStatus(Boolean status);
}
