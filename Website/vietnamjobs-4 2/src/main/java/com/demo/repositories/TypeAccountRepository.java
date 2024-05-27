package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.entities.TypeAccount;

public interface TypeAccountRepository extends CrudRepository<TypeAccount, Integer>{
	
	@Query("from TypeAccount where name = :name")
	public TypeAccount findbyname(@Param("name") String name);

}
