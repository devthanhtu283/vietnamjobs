package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.ApplicationHistory;
import com.demo.entities.Local;
import com.demo.entities.Postings;

public interface ApplicationHistoryRepository extends CrudRepository<ApplicationHistory, Integer>{
	@Query("from ApplicationHistory where seeker.id = :id")
	public ApplicationHistory findBySeekerID(@Param("id") int id);
	@Query("from ApplicationHistory where seeker.id = :id")
	public List<ApplicationHistory> findBySeekerID1(@Param("id") int id);
	
	@Query("from ApplicationHistory where postings.id = :id")
	public List<ApplicationHistory> findByPostingID(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM ApplicationHistory where postings.id = :id")
	int countByPostId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM ApplicationHistory")
	int countAll();

	@Query("SELECT COUNT(*) FROM ApplicationHistory WHERE result = :result")
	int countByResult(@Param("result") int result);

	@Query("SELECT COUNT(a) FROM ApplicationHistory a WHERE MONTH(a.created) = :month AND YEAR(a.created) = :year")
	int countByMonthAndYear(@Param("month") int month, @Param("year") int year);
	

	@Query("from ApplicationHistory where seeker.id = :id and status = :status and result = :result")
	public List<ApplicationHistory> findByStatusAndResult(@Param("id") int id, @Param("status") int status, @Param("result") int result);
	@Query("from ApplicationHistory where seeker.id = :id and postings.id = :postingID")
	public List<ApplicationHistory> findBySeekerIDAndPosting(@Param("id") int id, @Param("postingID") int postingID);
	
	@Query("from ApplicationHistory where seeker.id = :id and " +
		       "((status = 0 and result = 0) or " +
		       "(status = 1 and result in (0, 1, 2)))")
	public List<ApplicationHistory> findAppliedCV(@Param("id") int id);
}
