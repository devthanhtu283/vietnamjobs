package com.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.AdminDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;

public interface AdminService{
	
	public AdminDTO findByAccountId(int id);
	
	public boolean save(AdminDTO adminDTO);
	
}
