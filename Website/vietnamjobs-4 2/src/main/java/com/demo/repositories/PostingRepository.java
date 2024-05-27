package com.demo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.demo.entities.Postings;

public interface PostingRepository extends CrudRepository<Postings, Integer>{

	@Query("from Postings where category.name like  %:keyword%")
	public List<Postings> findbycategoryname(@Param("keyword") String keyword);
	
	@Query("from Postings where category.id = :id")
	public List<Postings> findbycategoryid(@Param("id") int id);
	
	@Query("from Postings where employer.id = :empid")
	public List<Postings> findByEmployerId(@Param("empid") int empid);
	
	
	@Query("from Postings where employer.name like  %:keyword%")
	public List<Postings> findbyemployername(@Param("keyword") String keyword);
	
	@Query("from Postings where category.name like  %:keyword%")
	public List<Postings> findbykeyword(@Param("keyword") String keyword);
	
	@Query("from Postings where category.name like  %:keyword%")
	public List<Postings> findbycategory(@Param("keyword") String keyword);
	
	@Query(value = "select * from Postings where employer_id = :employerid order by id desc limit :n", nativeQuery = true)
	public List<Postings> limit3(@Param("employerid") int employerid,@Param("n") int n);
	
	@Query("from Postings where status =:status")
	public List<Postings> findbystatus(@Param("status") boolean status);
	
	@Query("from Postings where employer.id = :employerid and status =:status")
	public List<Postings> findbyemployerstatus(@Param("employerid") int employerid,@Param("status") boolean status);
	
	@Query("from Postings where deadline >= :today")
	public List<Postings> findbydeadline(@Param("today") Date today);
	
	@Query("from Postings where employer.id = :employerid and deadline >= :today")
	public List<Postings> findbyemployeriddeadline(@Param("employerid") int employerid ,@Param("today") Date today);
	
	 @Query("SELECT p FROM Postings p " +
	           "JOIN p.local l " +
	           "JOIN p.wage w " +
	           "JOIN p.type t " +
	           "JOIN p.category c " +
	           "JOIN p.experience e " +
	           "WHERE (:localId IS NULL OR l.id = :localId) " +
	           "AND (:wageId IS NULL OR w.id = :wageId) " +
	           "AND (:typeId IS NULL OR t.id = :typeId) " +
	           "AND (:categoryId IS NULL OR c.id = :categoryId) " +
	           "AND (:experienceId IS NULL OR e.id = :experienceId) " +
	           "AND (p.title LIKE %:title% OR :title IS NULL)")
	    List<Postings> search(@Param("localId") Integer localId,
	                                          @Param("wageId") Integer wageId,
	                                          @Param("typeId") Integer typeId,
	                                          @Param("categoryId") Integer categoryId,
	                                          @Param("experienceId") Integer experienceId,
	                                          @Param("title") String title);
	 @Query("from Postings where title like  %:keyword%")
		public List<Postings> findbyemployertitle(@Param("keyword") String keyword);
	 
		@Query(value = "select * from postings order by rand() limit :n", nativeQuery = true)
		public List<Postings> findAllLimit(@Param("n") int n);
		
		@Query("from Postings where employer.id = :id")
		public List<Postings> getByEmployerId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where employer.id = :id")
	int countByEmployerId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where category.id = :id")
	int countByCategoryId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where local.id = :id")
	int countByLocalId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where experience.id = :id")
	int countByExperienceId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where rank.id = :id")
	int countByRankId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where type.id = :id")
	int countByTypeId(@Param("id") int id);

	@Query("SELECT COUNT(*) FROM Postings where wage.id = :id")
	int countByWageId(@Param("id") int id);

	@Query("SELECT COUNT(a) FROM Postings a WHERE MONTH(a.created) = :month AND YEAR(a.created) = :year")
	int countByMonthAndYear(@Param("month") int month, @Param("year") int year);

	@Modifying
	@Query(value = "UPDATE Postings SET status = :status WHERE id = :id", nativeQuery = true)
	int updateStatusById(@Param("id") int id, @Param("status") boolean status);
}

