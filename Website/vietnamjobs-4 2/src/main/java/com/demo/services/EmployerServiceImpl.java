package com.demo.services;

import java.util.List;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Admin;
import com.demo.entities.Employer;
import com.demo.repositories.EmployerRepository;
import com.demo.repositories.SeekerRepository;

@Service("employerService")
public class EmployerServiceImpl implements EmployerService{
	@Autowired
	private EmployerRepository employerRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	
	public EmployerDTO findByAccountID(int account_id) {
		return mapper.map(employerRepository.findByAccountID(account_id), EmployerDTO.class);
	}

	@Override
	public Employer getDetail(int id) {
		return employerRepository.findById(id).get();
	}

	@Override
	public boolean save(EmployerDTO employerDTO) {
		try {
			Employer employer =  mapper.map(employerDTO, Employer.class);
			employerRepository.save(employer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public EmployerDTO findbyname(String name) {
		return mapper.map(employerRepository.findbyname(name), EmployerDTO.class);
	}
	@Override
	public List<EmployerDTO> findAll() {
		return mapper.map(employerRepository.findAll(), new TypeToken<List<EmployerDTO>>() {}.getType());
	}

	@Override
	public Iterable<Employer> getAll() {
		return employerRepository.findAll();
	}

	@Transactional
	@Override
	public Boolean updateStatusById(int id, Boolean status) {
		return employerRepository.updateStatusById(id, status) > 0;
	}
	
	@Override
	public EmployerDTO findByID(int id) {
		return mapper.map(employerRepository.findByID(id), EmployerDTO.class);
	}
	@Override
	public EmployerDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.map(employerRepository.findByUsername(username),new EmployerDTO().getClass());
	}
	

	@Override
	public List<EmployerDTO> searchByName(String name) {
		return mapper.map(employerRepository.searchByName(name), new TypeToken<List<EmployerDTO>>() {}.getType());
	}
	
	@Override
	public Employer getByAccountId(int accountId) {
		return employerRepository.findByAccountID(accountId);
	}
	
	@Override
	public boolean save(Employer item) {
		try {
			employerRepository.save(item);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public Iterable<Employer> findTop(int limit) {
		return employerRepository.findTop(limit);
	}

	@Override
	public List<EmployerDTO> findAll2() {
		// TODO Auto-generated method stub
		return mapper.map(employerRepository.findAll2(6), new TypeToken<List<EmployerDTO>>() {}.getType());
	}
}
