package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.FollowDB;
import com.demo.dtos.FollowDTO;
import com.demo.entities.Follow;




public interface FollowService {
	public List<FollowDTO> findBySeekerId(int id);
	public FollowDTO findBySeekerId1(int id);
	public List<FollowDB> listPostFollowBySeekerID(int seeker_id);
	public List<FollowDB> listPostFollowBySeekerUsername(String username);
	public List<FollowDB> listPostFollowByEmployerID(int employer_id);
	public int countByEmployerId(int id);


}
