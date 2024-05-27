package com.demo.services;

import com.demo.entities.Experience;

import java.util.List;

public interface ExperienceService {

    public Experience find(int id);

    public Iterable<Experience> findAll();

    public boolean save(Experience local);

    public boolean delete(int id);

    public boolean exists(String name, int id);


//	public boolean existsPost(int id);

    public List<Experience> findAllByStatus(Boolean status);
}
