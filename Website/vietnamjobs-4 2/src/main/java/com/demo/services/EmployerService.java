package com.demo.services;


import java.util.List;

import com.demo.dtos.EmployerDTO;
import com.demo.entities.Employer;


public interface EmployerService {
	
	public List<EmployerDTO> findAll();
	
	public List<EmployerDTO> findAll2();
	
	public Iterable<Employer> getAll();

	public EmployerDTO findByAccountID(int account_id);

	public Employer getDetail(int id);

	public boolean save(EmployerDTO employerDTO);
	
	public EmployerDTO findbyname(String name);

	public Boolean updateStatusById(int id, Boolean status);
	
	public EmployerDTO findByID(int id);
	
	public EmployerDTO findByUsername(String username);
	
	public List<EmployerDTO> searchByName(String name);
	
	public Employer getByAccountId(int accountId);
	
	public boolean save(Employer item);

	public Iterable<Employer> findTop(int limit);

}
