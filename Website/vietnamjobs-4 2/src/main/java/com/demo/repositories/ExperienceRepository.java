package com.demo.repositories;

import com.demo.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExperienceRepository extends CrudRepository<Experience, Integer>{
	
	@Query("from Experience where name= :name")
	public Experience findByName(@Param("name") String name);

	@Query("SELECT COUNT(*) FROM Experience WHERE name = :name AND id != :id")
	public int exists(@Param("name") String name, @Param("id") int id);

	@Query("from Experience where status = :status")
	public List<Experience > findAllByStatus(@Param("status") Boolean status);

}
