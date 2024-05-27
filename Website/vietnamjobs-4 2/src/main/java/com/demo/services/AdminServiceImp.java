package com.demo.services;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.demo.dtos.AdminDTO;
import com.demo.entities.Admin;

import com.demo.repositories.AdminRepository;

@Service("adminService")
public class AdminServiceImp implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public AdminDTO findByAccountId(int id) {
		// TODO Auto-generated method stub
		return mapper.map(adminRepository.findByAccountID(id), new AdminDTO().getClass());
	}

	@Override
	public boolean save(AdminDTO adminDTO) {
		try {
			Admin admin =  mapper.map(adminDTO, Admin.class);
			adminRepository.save(admin);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
