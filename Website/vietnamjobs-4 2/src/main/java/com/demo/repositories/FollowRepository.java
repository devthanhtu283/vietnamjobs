package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.FollowDB;
import com.demo.entities.Follow;


public interface FollowRepository extends CrudRepository<Follow, Integer>{
	@Query("from Follow where seeker.id = :id")
	public List<Follow> findBySeekerId(@Param("id") int id);
	
	@Query("from Follow where employer.id = :id")
	public List<Follow> findByEmployerId(@Param("id") int id);
	
	@Query("from Follow where seeker.id = :id")
	public Follow findBySeekerId1(@Param("id") int id);
	
	@Query("from Follow where seeker.account.username = :username")
	public List<Follow> findBySeekerUsername(@Param("username") String username);
	
	@Query("SELECT new com.demo.dtos.FollowDB(fl.seeker.id as seekerID, fl.employer.id as employerID, em.name as employerName, po.id as postingID, po.title as postingTitle) " +
		       "FROM Follow fl " +
		       "JOIN Postings po ON po.employer.id = fl.employer.id " +
		       "JOIN Employer em ON em.id = fl.employer.id " +
		       "WHERE fl.seeker.id = :seekerId")
	public List<FollowDB> listPostFollowBySeekerID(@Param("seekerId") int seekerId);

	@Query("SELECT COUNT(*) FROM Follow where employer.id = :id")
	int countByEmployerId(@Param("id") int id);
}
