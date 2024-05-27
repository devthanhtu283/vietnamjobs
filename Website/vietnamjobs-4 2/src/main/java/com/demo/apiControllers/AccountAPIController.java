package com.demo.apiControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.AccountDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.services.AccountService;
import com.demo.services.PostingService;


@Controller
@RequestMapping("api/account")
public class AccountAPIController {
	@Autowired
	private AccountService accountService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<AccountDTO>> findAll(){
		try {
			return new ResponseEntity<List<AccountDTO>>(accountService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "login", produces = MimeTypeUtils.APPLICATION_JSON_VALUE, consumes = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> login(@RequestBody AccountDTO accountDTO){
		try {
			
			return new ResponseEntity<Object>(new Object() {
				public boolean status = accountService.login1(accountDTO.getUsername(), accountDTO.getPassword());
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findByUsername/{username}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDTO> findByUsername(@PathVariable("username") String username){
		try {
			return new ResponseEntity<AccountDTO>(accountService.findByUsername(username), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<AccountDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping(value = "update", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, 
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> update(@RequestBody AccountDTO accountDTO) {
			try {
				return new ResponseEntity<Object>(new Object() {
					public boolean result = accountService.save(accountDTO);
				}, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	@PostMapping(value = "create", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody AccountDTO accountDTO) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean result = accountService.save(accountDTO);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
