package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Local;

import java.util.List;

public interface LocalRepository extends CrudRepository<Local, Integer>{


	@Query("from Local where name = :name")
	public Local findbyname(@Param("name") String name);

	@Query("SELECT COUNT(*) FROM Local WHERE name = :name AND id != :id")
	public int exists(@Param("name") String name, @Param("id") int id);

	@Query("from Local where status = :status")
	public List<Local> findAllByStatus(@Param("status") Boolean status);

}
