package com.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.demo.dtos.AccountDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;

public interface AccountService extends UserDetailsService {
	
	public List<AccountDTO> findAll();
	
	public boolean save(AccountDTO accountDTO);
	
	public boolean delete(int id);
	
	public boolean login(String username, String password);
	
	public boolean login1(String username, String password);
	
	public AccountDTO findById(int id);
	
	public AccountDTO findByUsername(String username);
	
	public AccountDTO findByEmail(String email);
	
	public Iterable<Account> getAll();

	public Account getDetail(int id);

	public Boolean updateStatusById(int id, Boolean status);
	
	public Account find(int id);
	
	public Account getByUsername(String username);

	public int countByRoleAndMonthAndYear(int roleId, int month, int year);
	public int countByRole(int roleId);
}
