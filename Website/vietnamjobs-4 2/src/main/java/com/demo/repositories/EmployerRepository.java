package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Employer;


public interface EmployerRepository extends CrudRepository<Employer, Integer>{
	
	@Query("from Employer where account.id = :account_id")
	public Employer findByAccountID(@Param("account_id") int account_id);
	

	@Query(value = "select * from employer order by id desc limit :n", nativeQuery = true)
	public List<Employer> findAll2(@Param("n") int n);
	
	
	@Query("from Employer where name = :name")
	public Employer findbyname(@Param("name") String name);
	
	@Query(value = "select * from employer where status =:status order by id desc limit :n", nativeQuery = true)
	public List<Employer> limitbyid(@Param("status") boolean status,@Param("n") int n);

	@Modifying
	@Query(value = "UPDATE Employer SET status = :status WHERE id = :id", nativeQuery = true)
	int updateStatusById(@Param("id") int id, @Param("status") boolean status);


	@Query("from Employer where id = :id")
	public Employer findByID(@Param("id") int id);
	
	@Query("from Employer where account.username = :username")
	public Employer findByUsername(@Param("username") String username);
	
	@Query("from Employer where name like %:name%")
	public List<Employer> searchByName(@Param("name") String name);

	@Query(value = "SELECT c.*, COUNT(p.id) AS post_count FROM Employer c JOIN Postings p ON c.id = p.employer_id GROUP BY c.id, c.name ORDER BY post_count DESC LIMIT :n", nativeQuery = true)
	List<Employer> findTop(@Param("n") int n);

}
 