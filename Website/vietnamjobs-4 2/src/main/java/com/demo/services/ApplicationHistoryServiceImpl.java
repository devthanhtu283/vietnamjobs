package com.demo.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.entities.ApplicationHistory;
import com.demo.entities.Postings;
import com.demo.entities.Seeker;
import com.demo.repositories.ApplicationHistoryRepository;

@Service("applicationHistoryService")
public class ApplicationHistoryServiceImpl implements ApplicationHistoryService{
	@Autowired 
	private ApplicationHistoryRepository applicationHistoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public List<ApplicationHistoryDTO> findAll() {
		return modelMapper.map(applicationHistoryRepository.findAll(), new TypeToken<List<ApplicationHistoryDTO>>() {}.getType());
	}
	public static void main(String[] args) {
		System.out.println(new ApplicationHistoryServiceImpl().findAll());
	}
	@Override
	public ApplicationHistoryDTO findBySeekerID(int id) {
		return modelMapper.map(applicationHistoryRepository.findBySeekerID(id), ApplicationHistoryDTO.class);
	}
	@Override
	public boolean save(ApplicationHistoryDTO applicationHistoryDTO) {
		// TODO Auto-generated method stub
			try {
				ApplicationHistory applicationHistory =  modelMapper.map(applicationHistoryDTO, ApplicationHistory.class);
				Postings postings = new Postings();
				postings.setId(applicationHistoryDTO.getPostingID());
				Seeker seeker = new Seeker();
				seeker.setId(applicationHistoryDTO.getSeekerID());
				applicationHistory.setPostings(postings);
				applicationHistory.setSeeker(seeker);
				applicationHistory.setCreated(new Date());
				applicationHistoryRepository.save(applicationHistory);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		
	}
	@Override
	public ApplicationHistoryDTO findByID(int id) {
		return modelMapper.map(applicationHistoryRepository.findById(id).get(), ApplicationHistoryDTO.class);
	}
	
	@Override
	public List<ApplicationHistoryDTO> findBySeekerID1(int id) {
		return modelMapper.map(applicationHistoryRepository.findBySeekerID1(id), new TypeToken<List<ApplicationHistoryDTO>>() {}.getType());
	}
	@Override
	public List<ApplicationHistory> findByPostingID(int id) {
		return modelMapper.map(applicationHistoryRepository.findByPostingID(id), new TypeToken<List<ApplicationHistoryDTO>>() {}.getType());
	}

	@Override
	public boolean existByPostId(int id) {
		return applicationHistoryRepository.countByPostId(id) > 0;
	}

	@Override
	public int countAll() {
		if(applicationHistoryRepository.countAll() > 0){
			return applicationHistoryRepository.countAll();
		}else{
			return 0;
		}
	}

	@Override
	public int countByResult(int result) {
		if(applicationHistoryRepository.countByResult(result) > 0){
			return applicationHistoryRepository.countByResult(result);
		}else{
			return 0;
		}
	}

	@Override
	public int countByMonthAndYear(int month, int year) {
		if(applicationHistoryRepository.countByMonthAndYear(month,year) > 0){
			return applicationHistoryRepository.countByMonthAndYear(month, year);
		}else{
			return 0;
		}
	}
	@Override
	public List<ApplicationHistoryDTO> findByStatusAndResult(int id, int status, int result) {
		return modelMapper.map(applicationHistoryRepository.findByStatusAndResult(id, status, result), new TypeToken<List<ApplicationHistoryDTO>>() {}.getType());
	}
	
	@Override
	public ApplicationHistoryDTO findBySeekerIDAndPosting(int id, int postingID) {
		return modelMapper.map(applicationHistoryRepository.findBySeekerIDAndPosting(id, postingID).get(0), ApplicationHistoryDTO.class);
	}
	@Override
	public List<ApplicationHistoryDTO> findAppliedCV(int id) {
		return modelMapper.map(applicationHistoryRepository.findAppliedCV(id), new TypeToken<List<ApplicationHistoryDTO>>() {}.getType());
	}
}
