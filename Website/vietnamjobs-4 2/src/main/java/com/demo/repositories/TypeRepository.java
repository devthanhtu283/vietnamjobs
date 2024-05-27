package com.demo.repositories;

import com.demo.entities.Type;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TypeRepository extends CrudRepository<Type, Integer>{
	
	@Query("from Type where name = :name")
	public Type findbyname(@Param("name") String name);

	@Query("SELECT COUNT(*) FROM Type WHERE name = :name AND id != :id")
	int exists(@Param("name") String name, @Param("id") int id);

	@Query("from Type where status = :status")
	List<Type> findAllByStatus(@Param("status") Boolean status);

}
