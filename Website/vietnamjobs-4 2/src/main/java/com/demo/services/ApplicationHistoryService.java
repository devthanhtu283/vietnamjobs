package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.ApplicationHistory;

public interface ApplicationHistoryService {
	public List<ApplicationHistoryDTO> findAll();
	public ApplicationHistoryDTO findBySeekerID(int id);
	public boolean save(ApplicationHistoryDTO applicationHistoryDTO);
	public ApplicationHistoryDTO findByID(int id);
	public List<ApplicationHistoryDTO> findBySeekerID1(int id);
	public List<ApplicationHistory> findByPostingID(int id);


	public boolean existByPostId(int id);
	public int countAll();
	public int countByResult(int result);
	public int countByMonthAndYear(int month, int year);
	public List<ApplicationHistoryDTO> findByStatusAndResult(int id, int status, int result);
	public ApplicationHistoryDTO findBySeekerIDAndPosting(int id, int postingID);
	public List<ApplicationHistoryDTO> findAppliedCV(int id);

}
