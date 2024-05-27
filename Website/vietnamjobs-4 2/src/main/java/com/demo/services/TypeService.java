package com.demo.services;

import com.demo.entities.*;

import java.util.List;

public interface TypeService {

    public Type find(int id);

    public Iterable<Type> findAll();

    public boolean save(Type local);

    public boolean delete(int id);

    public boolean exists(String name, int id);


//	public boolean existsPost(int id);

    public List<Type> findAllByStatus(Boolean status);
}
