package com.demo.services;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.demo.dtos.PostingDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.dtos.TransactionHistoryDTO;
import com.demo.entities.Category;
import com.demo.entities.Experience;
import com.demo.entities.Local;
import com.demo.entities.TransactionHistory;
import com.demo.entities.Type;
import com.demo.entities.TypeAccount;

public interface TransactionHistoryService {
	
	public boolean delete(int id);
	
	public boolean save(TransactionHistoryDTO transactionHistoryDTO);
	
	public List<TransactionHistoryDTO> findAll();
	
	public TransactionHistoryDTO findbyid(int id);
	
	public List<TransactionHistoryDTO> findbyaccountid(int accountid);

	
	public double totalbyaccountid(int accountid);
}
