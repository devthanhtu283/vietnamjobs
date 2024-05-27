package com.demo.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.SeekerDTO;
import com.demo.entities.Admin;
import com.demo.entities.Seeker;
import com.demo.entities.TypeAccount;
import com.demo.repositories.SeekerRepository;
import com.demo.repositories.TypeAccountRepository;

@Service("typeAccountService")
public class TypeAccountServiceImpl implements TypeAccountService{

	@Autowired
	private TypeAccountRepository typeAccountRepository;
	
	
	
}
