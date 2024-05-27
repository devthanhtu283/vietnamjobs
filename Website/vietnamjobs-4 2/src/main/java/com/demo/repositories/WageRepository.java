package com.demo.repositories;

import com.demo.entities.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WageRepository extends CrudRepository<Wage, Integer>{
	
	
	@Query("from Wage where name =:name")
	public Wage findbyname(@Param("name") String name);

	@Query("SELECT COUNT(*) FROM Wage WHERE min = :min AND max = :max AND id != :id")
	int exists(@Param("min") int min,@Param("max") int max, @Param("id") int id);

	@Query("from Wage where status = :status")
	List<Wage> findAllByStatus(@Param("status") Boolean status);

}
