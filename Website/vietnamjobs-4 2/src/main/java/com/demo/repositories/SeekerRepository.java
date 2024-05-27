package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.SeekerDTO;
import com.demo.entities.Account;
import com.demo.entities.Rank;
import com.demo.entities.Seeker;

public interface SeekerRepository extends CrudRepository<Seeker, Integer>{
	@Query("from Seeker where account.id = :account_id")
	public Seeker findByAccountID(@Param("account_id") int account_id);
	
	@Query("from Seeker where fullname like %:fullname%")
	public List<Seeker> findByfullname(@Param("fullname") String fullname);

	
	@Query("from Seeker where account.username = :username")
	public Seeker findByusername(@Param("username") String username);
}

