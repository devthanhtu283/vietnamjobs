package com.demo.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Account;
import com.demo.entities.Postings;

public interface AccountRepository extends CrudRepository<Account, Integer>{
	
	@Query("from Account where username = :username and password = :password and status = :status")
	public Account login(@Param("username") String username, @Param("password") String password,
			@Param("status") boolean status);

	@Query("from Account where email = :email")
	public Account findByEmail(@Param("email") String email);
	
	@Query("from Account where username = :username")
	public Account findbyUsername(@Param("username") String username);
	
	@Modifying
	@Query(value = "UPDATE Account SET status = :status WHERE id = :id", nativeQuery = true)
	int updateStatusById(@Param("id") int id, @Param("status") boolean status);
	
	@Query("from Account where username = :username")
	Account getByUsername(@Param("username") String username);

	@Query("SELECT COUNT(a) FROM Account a WHERE a.typeAccount.id = :roleId AND MONTH(a.created) = :month AND YEAR(a.created) = :year")
	int countByRoleAndMonthAndYear(@Param("roleId") int roleId, @Param("month") int month, @Param("year") int year);

	@Query("SELECT COUNT(a) FROM Account a WHERE a.typeAccount.id = :roleId")
	int countByRole(@Param("roleId") int roleId);

}
