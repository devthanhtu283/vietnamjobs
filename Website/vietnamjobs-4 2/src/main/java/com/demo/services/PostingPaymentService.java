package com.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.AdminDTO;
import com.demo.dtos.FollowDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;

public interface PostingPaymentService{
	
	public List<PostingspaymentDTO> findAll();
	
	public List<PostingspaymentDTO> limit(boolean status, int limit);
	
	public List<PostingspaymentDTO> limitbycategory(boolean status, String categoryname, int limit);
	
	public List<PostingspaymentDTO> findbypostingsid( int postingsid, boolean status);
	
	public List<PostingspaymentDTO> orderbycost();
	
	public PostingspaymentDTO findbyid(int id);

	public boolean save(PostingspaymentDTO postingspaymentDTO);
	
	public boolean delete(int id);
	
	
	
}
