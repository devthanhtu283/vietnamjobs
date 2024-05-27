package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.dtos.PostingDTO;
import com.demo.dtos.SeekerDTO;

public interface SeekerService {
	public SeekerDTO findByAccountID(int account_id);
	
	public boolean save(SeekerDTO seekerDTO);
	
	public boolean delete(int id);

	public List<SeekerDTO> findAll();

	public SeekerDTO findbyusername(String username);

	public SeekerDTO findbyid(int id);

	public List<SeekerDTO> findbyFullname(String fullname);
}
