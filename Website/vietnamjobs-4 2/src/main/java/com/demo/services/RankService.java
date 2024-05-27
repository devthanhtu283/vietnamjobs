package com.demo.services;

import com.demo.entities.Rank;

import java.util.List;

public interface RankService {

    public Rank find(int id);

    public Iterable<Rank> findAll();

    public boolean save(Rank local);

    public boolean delete(int id);

    public boolean exists(String name, int id);


//	public boolean existsPost(int id);

    public List<Rank> findAllByStatus(Boolean status);
}
