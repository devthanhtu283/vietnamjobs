package com.demo.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.entities.TransactionHistory;

public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Integer>{
	
	@Query("from TransactionHistory where account.id = :account_id")
	public TransactionHistory findByAccountId(@Param("account_id") int account_id);

	
	@Query("select sum(total) from TransactionHistory where account.id =:account_id")
	public double totalbyemployerid(@Param("account_id") int account_id);
}
