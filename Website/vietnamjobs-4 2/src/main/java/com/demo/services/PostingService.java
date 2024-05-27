package com.demo.services;

import java.util.Date;
import java.util.List;

import com.demo.dtos.PostingspaymentDTO;
import org.springframework.data.repository.query.Param;

import com.demo.dtos.AdminDTO;
import com.demo.dtos.PostingDTO;
import com.demo.entities.Experience;
import com.demo.entities.Postings;

public interface PostingService {
	public List<PostingDTO> findAll();
	public List<PostingDTO> findByEmployerId(int empid);
	public List<PostingDTO> findByEmployerName(String name);
	public List<PostingDTO> findByEmployerIdlimit(int accountid, int limit);
	public List<PostingDTO> findByStatus(boolean status);
	public List<PostingDTO> findByEmployerIdStatus(int employerid, boolean status);
	public List<PostingDTO> findByDeadline();
	public List<PostingDTO> findByEmployerIdDeadline(int employerid);
	
	
	public boolean save(PostingDTO postingDTO);
	public PostingDTO findById(int id);
	public boolean delete(int id);
	
	public List<PostingDTO> search(Integer localId, Integer wageId, Integer typeId, Integer categoryId, Integer experienceId, String title);
	
	public List<PostingDTO> findByTittle(String name);
	public List<PostingDTO> findAllLimit(int limit);
	
	public Iterable<Postings> getByEmployerId(int id);
	
	public Postings getDetail(int id);
	
	public boolean saveDB(Postings item);
	public int countByEmployerId(int id);
	public boolean existPostByCategoryId(int id);
	public boolean existPostByLocalId(int id);
	public boolean existPostByExperienceId(int id);
	public boolean existPostByRankId(int id);
	public boolean existPostByTypeId(int id);
	public boolean existPostByWageId(int id);
	public int countByMonthAndYear(int month, int year);
	public Postings find(int id);

	public Boolean updateStatusById(int id, Boolean status);
	public Iterable<Postings> getAll();
}
