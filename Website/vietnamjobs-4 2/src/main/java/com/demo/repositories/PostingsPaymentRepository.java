package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.PostingspaymentDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.entities.Postingspayment;
import com.demo.entities.Seeker;

public interface PostingsPaymentRepository extends CrudRepository<Postingspayment, Integer>{
	
	
	@Query(value = "from Postingspayment where status =:status order by cost desc limit :n")
	public List<Postingspayment> limit(@Param("status") boolean status,@Param("n") int n);
	
	@Query(value = "from Postingspayment where postings.id =:postingsid and status =:status")
	public List<Postingspayment> findbypostingsid(@Param("postingsid") int postingsid,@Param("status") boolean status);
	
	@Query(value = "from Postingspayment where status =:status and postings.category.name =:categoryname order by cost desc limit :n")
	public List<Postingspayment> limitbycategory(@Param("status") boolean status,@Param("n") int n, @Param("categoryname") String categoryname);
	
	@Query(value = "from Postingspayment where status =:status order by cost desc")
	public List<Postingspayment> orderbycost(@Param("status") boolean status);
	

	
	
	
}
