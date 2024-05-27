package com.demo.services;



import java.util.List;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.FollowDB;
import com.demo.dtos.FollowDTO;

import com.demo.repositories.FollowRepository;


@Service("followService")
public class FollowServiceImpl implements FollowService{
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Override
	public List<FollowDTO> findBySeekerId(int id) {
		return mapper.map(followRepository.findBySeekerId(id), new TypeToken<List<FollowDTO>>() {
		}.getType());
	}
	@Override
	public FollowDTO findBySeekerId1(int id) {
		try {
			return mapper.map(followRepository.findBySeekerId1(id), FollowDTO.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public List<FollowDB> listPostFollowBySeekerID(int seeker_id) {
		return followRepository.listPostFollowBySeekerID(seeker_id);
	}
	@Override
	public List<FollowDB> listPostFollowByEmployerID(int employer_id) {
		// TODO Auto-generated method stub
		return mapper.map(followRepository.findByEmployerId(employer_id), new TypeToken<List<FollowDTO>>() {
		}.getType());
	}
	@Override
	public List<FollowDB> listPostFollowBySeekerUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.map(followRepository.findBySeekerUsername(username), new TypeToken<List<FollowDTO>>() {
		}.getType());
	}

	@Override
	public int countByEmployerId(int id) {
		if(followRepository.countByEmployerId(id) > 0){
			return followRepository.countByEmployerId(id);
		}else{
			return 0;
		}
	}
	

	
	
	
	



}
