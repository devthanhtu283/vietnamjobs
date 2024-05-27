package com.demo.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.dtos.TransactionHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.Admin;
import com.demo.entities.Category;
import com.demo.entities.Follow;
import com.demo.entities.Local;
import com.demo.entities.Seeker;
import com.demo.entities.TransactionHistory;
import com.demo.entities.Type;
import com.demo.entities.TypeAccount;
import com.demo.repositories.CategoryRepository;
import com.demo.repositories.LocalRepository;
import com.demo.repositories.SeekerRepository;
import com.demo.repositories.TransactionHistoryRepository;
import com.demo.repositories.TypeAccountRepository;
import com.demo.repositories.TypeRepository;

@Service("transactionHistoryService")
public class TransactionHistoryServiceImpl implements TransactionHistoryService{
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			transactionHistoryRepository.delete(transactionHistoryRepository.findById(id).get());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	

	@Override
	public boolean save(TransactionHistoryDTO transactionHistoryDTO) {
		// TODO Auto-generated method stub
		try {
			TransactionHistory transactionHistory = mapper.map(transactionHistoryDTO, TransactionHistory.class);
			Account account = new Account();
			account.setId(transactionHistoryDTO.getAccountid());
			transactionHistory.setAccount(account);
			transactionHistoryRepository.save(transactionHistory);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<TransactionHistoryDTO> findAll() {
		// TODO Auto-generated method stub
		return mapper.map(transactionHistoryRepository.findAll(), new TypeToken<List<TransactionHistoryDTO>>() {}.getType());
	}


	@Override
	public TransactionHistoryDTO findbyid(int id) {
		// TODO Auto-generated method stub
		return mapper.map(transactionHistoryRepository.findById(id).get(), TransactionHistoryDTO.class);
	}


	@Override
	public List<TransactionHistoryDTO> findbyaccountid(int accountid) {
		// TODO Auto-generated method stub
		return mapper.map(transactionHistoryRepository.findByAccountId(accountid), new TypeToken<List<TransactionHistoryDTO>>() {}.getType());
	}




	@Override
	public double totalbyaccountid(int accountid) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
