package com.demo.repositories;

import com.demo.entities.Rank;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RankRepository extends CrudRepository<Rank, Integer> {


    @Query("from Rank where name = :name")
    Rank findbyname(@Param("name") String name);

    @Query("SELECT COUNT(*) FROM Rank WHERE name = :name AND id != :id")
    int exists(@Param("name") String name, @Param("id") int id);

    @Query("from Rank where status = :status")
    List<Rank> findAllByStatus(@Param("status") Boolean status);


}
