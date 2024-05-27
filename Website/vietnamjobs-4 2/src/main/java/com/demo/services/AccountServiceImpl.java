package com.demo.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.demo.dtos.AccountDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.repositories.AccountRepository;
import com.demo.repositories.PostingRepository;

import jakarta.transaction.Transactional;
@Service("accountService")
public class AccountServiceImpl implements AccountService{
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ModelMapper mapper;
	private BCrypt crypt;
	@Override
	public List<AccountDTO> findAll() {
		return mapper.map(accountRepository.findAll(), new TypeToken<List<AccountDTO>>() {}.getType());
	}

	@Override
	public boolean save(AccountDTO accountDTO) {
		try {
			Account account =  mapper.map(accountDTO, Account.class);
			accountRepository.save(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean login1(String username, String password) {
		try {
			Account account = accountRepository.findbyUsername(username);
			if(account != null) {
				return crypt.checkpw(password, account.getPassword());
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean login(String username, String password) {
		return accountRepository.login(username, password, true) != null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountDTO account = mapper.map(accountRepository.findbyUsername(username), new AccountDTO().getClass());
		if(account == null) {
			throw new UsernameNotFoundException("Username not found");
		} else {
			List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(account.getTypeAccount()));
			return new User(username, account.getPassword() ,authorities);
		}
	}

	@Override
	public AccountDTO findById(int id) {
		// TODO Auto-generated method stub
		return mapper.map(accountRepository.findById(id), new AccountDTO().getClass());
	}

	@Override
	public AccountDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.map(accountRepository.findByEmail(email), new AccountDTO().getClass());
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		try {
			accountRepository.delete(accountRepository.findById(id).get());
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public AccountDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		return mapper.map(accountRepository.findbyUsername(username), new AccountDTO().getClass());
	}

	   @Override
	    public Iterable<Account> getAll() {
	        return accountRepository.findAll();
	    }

	    @Override
	    public Account getDetail(int id) {
	        return accountRepository.findById(id).get();
	    }

	    @Override
	    public Account find(int id) {
	        return accountRepository.findById(id).get();
	    }
	    @Transactional
	    @Override
	    public Boolean updateStatusById(int id, Boolean status) {
	        return accountRepository.updateStatusById(id, status) > 0;
	    }
	    

		@Override
		public Account getByUsername(String username) {
			return accountRepository.getByUsername(username);
		}

	@Override
	public int countByRoleAndMonthAndYear(int roleId, int month, int year) {
		if(accountRepository.countByRoleAndMonthAndYear(roleId, month, year) > 0){
			return accountRepository.countByRoleAndMonthAndYear(roleId, month, year);
		}else{
			return 0;
		}
	}

	@Override
	public int countByRole(int roleId) {
		if(accountRepository.countByRole(roleId) > 0){
			return accountRepository.countByRole(roleId);
		}else{
			return 0;
		}
	}
}
