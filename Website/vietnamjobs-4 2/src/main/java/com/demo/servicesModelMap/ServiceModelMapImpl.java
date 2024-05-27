package com.demo.servicesModelMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Admin;
import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.Experience;
import com.demo.entities.Local;
import com.demo.entities.Rank;
import com.demo.entities.Type;
import com.demo.entities.TypeAccount;
import com.demo.entities.Wage;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.EmployerRepository;
import com.demo.repositories.ExperienceRepository;
import com.demo.repositories.LocalRepository;
import com.demo.repositories.RankRepository;
import com.demo.repositories.SeekerRepository;
import com.demo.repositories.TypeAccountRepository;
import com.demo.repositories.TypeRepository;
import com.demo.repositories.WageRepository;

@Service()
public class ServiceModelMapImpl implements ServiceModelMap{
	@Autowired
	private EmployerRepository employerRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private TypeAccountRepository typeAccountRepository;
	
	@Autowired
	private ExperienceRepository experienceRepository;
	
	@Autowired
	private LocalRepository localRepository;
	
	@Autowired
	private RankRepository rankRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private WageRepository wageRepository;
	
	

	
	@Override
	public Employer findbyemployername(String name) {
		// TODO Auto-generated method stub
		return employerRepository.findbyname(name);
	}

	@Override
	public Category findbycategoryname(String name) {
		// TODO Auto-generated method stub
		return categoryRepository.findbyname(name);
	}

	@Override
	public TypeAccount findbytypeaccountname(String name) {
		// TODO Auto-generated method stub
		return typeAccountRepository.findbyname(name);
	}

	@Override
	public Experience findbyexpname(String name) {
		// TODO Auto-generated method stub
		return experienceRepository.findByName(name);
	}

	@Override
	public Local findbylocalname(String name) {
		// TODO Auto-generated method stub
		return localRepository.findbyname(name);
	}


	@Override
	public Rank findbyrankname(String name) {
		// TODO Auto-generated method stub
		return rankRepository.findbyname(name);
	}

	@Override
	public Type findbytypename(String name) {
		// TODO Auto-generated method stub
		return typeRepository.findbyname(name);
	}

	@Override
	public Wage findbywagename(String name) {
		// TODO Auto-generated method stub
		return wageRepository.findbyname(name);
	}
	
	
}
