package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Admin;
import com.demo.entities.Seeker;
import com.demo.repositories.SeekerRepository;

@Service("seekerService")
public class SeekerServiceImpl implements SeekerService{
	@Autowired
	private SeekerRepository seekerRepository;
	@Autowired
	private ModelMapper mapper;
	@Override
	
	public SeekerDTO findByAccountID(int account_id) {
		return mapper.map(seekerRepository.findByAccountID(account_id),new SeekerDTO().getClass());
	}
	@Override
	public boolean save(SeekerDTO seekerDTO) {
		// TODO Auto-generated method stub
		try {
			Seeker seeker =  mapper.map(seekerDTO, Seeker.class);
			seekerRepository.save(seeker);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			seekerRepository.delete(seekerRepository.findById(id).get());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public List<SeekerDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.map(seekerRepository.findAll(), new TypeToken<List<SeekerDTO>>() {}.getType());
	}
	@Override
	public SeekerDTO findbyusername(String username) {
		// TODO Auto-generated method stub
		return mapper.map(seekerRepository.findByusername(username), new SeekerDTO().getClass());
	}
	@Override
	public SeekerDTO findbyid(int id) {
		// TODO Auto-generated method stub
		return mapper.map(seekerRepository.findById(id), new SeekerDTO().getClass());
	}
	@Override
	public List<SeekerDTO> findbyFullname(String fullname) {
		// TODO Auto-generated method stub
		return mapper.map(seekerRepository.findByfullname(fullname), new TypeToken<List<SeekerDTO>>() {}.getType());
	}
}
