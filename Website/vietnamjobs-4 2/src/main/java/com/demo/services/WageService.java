package com.demo.services;

import com.demo.entities.Wage;

import java.util.List;

public interface WageService {

    public Wage find(int id);

    public Iterable<Wage> findAll();

    public boolean save(Wage local);

    public boolean delete(int id);

    public boolean exists(int min, int max,int id);

//	public boolean existsPost(int id);

    public List<Wage> findAllByStatus(Boolean status);

}
