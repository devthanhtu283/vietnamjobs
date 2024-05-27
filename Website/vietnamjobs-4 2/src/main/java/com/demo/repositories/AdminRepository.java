package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Account;
import com.demo.entities.Admin;
import com.demo.entities.Postings;
import com.demo.entities.Seeker;

public interface AdminRepository extends CrudRepository<Admin, Integer>{
	
	@Query("from Admin where account.id = :account_id")
	public Admin findByAccountID(@Param("account_id") int account_id);
	
	@Query("from Admin where account.username = :username")
	public Admin findByUsername(@Param("username") String username);


}
