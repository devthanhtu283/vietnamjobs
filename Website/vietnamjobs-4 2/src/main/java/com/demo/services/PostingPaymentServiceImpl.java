package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;


import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.FollowDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.PostingspaymentDTO;
import com.demo.entities.Account;
import com.demo.entities.Follow;
import com.demo.entities.Local;
import com.demo.entities.Postingspayment;
import com.demo.entities.Seeker;
import com.demo.repositories.FollowRepository;
import com.demo.repositories.LocalRepository;
import com.demo.repositories.PostingsPaymentRepository;



@Service("postingsPaymentService")
public class PostingPaymentServiceImpl implements PostingPaymentService{

	@Autowired
	private PostingsPaymentRepository postingsPaymentRepository;
	@Autowired 
	private ModelMapper mapper;
	@Override
	public List<PostingspaymentDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.findAll(), new TypeToken<List<PostingspaymentDTO>>() {}.getType());
	}
	@Override
	public PostingspaymentDTO findbyid(int id) {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.findById(id).get(), new PostingspaymentDTO().getClass());
	}
	@Override
	public boolean save(PostingspaymentDTO postingspaymentDTO) {
		// TODO Auto-generated method stub
		try {
			Postingspayment postingspayment =  mapper.map(postingspaymentDTO, Postingspayment.class);
			postingsPaymentRepository.save(postingspayment);
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
			postingsPaymentRepository.delete(postingsPaymentRepository.findById(id).get());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}

	}
	@Override
	public List<PostingspaymentDTO> limit(boolean status, int limit) {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.limit(status, limit), new TypeToken<List<PostingspaymentDTO>>() {}.getType());
	}
	@Override
	public List<PostingspaymentDTO> limitbycategory(boolean status, String categoryname, int limit) {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.limitbycategory(status, limit, categoryname), new TypeToken<List<PostingspaymentDTO>>() {}.getType());
	}
	@Override
	public List<PostingspaymentDTO> findbypostingsid(int postingsid, boolean status) {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.findbypostingsid(postingsid, status), new TypeToken<List<PostingspaymentDTO>>() {}.getType());
	}
	@Override
	public List<PostingspaymentDTO> orderbycost() {
		// TODO Auto-generated method stub
		return mapper.map(postingsPaymentRepository.orderbycost(true), new TypeToken<List<PostingspaymentDTO>>() {}.getType());
	}
	
}
