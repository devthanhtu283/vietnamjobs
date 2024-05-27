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
import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.services.AccountService;
import com.demo.services.ApplicationHistoryService;
import com.demo.services.PostingService;


@Controller
@RequestMapping("api/applicationHistory")
public class ApplicationHistoryController {
	@Autowired
	private ApplicationHistoryService applicationHistoryService;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApplicationHistoryDTO>> findAll(){
		try {
			return new ResponseEntity<List<ApplicationHistoryDTO>>(applicationHistoryService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ApplicationHistoryDTO>>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping(value = "update", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, 
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> update(@RequestBody ApplicationHistoryDTO applicationHistoryDTO) {
			try {
				return new ResponseEntity<Object>(new Object() {
					public boolean result = applicationHistoryService.save(applicationHistoryDTO);
				}, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	@PostMapping(value = "create", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> create(@RequestBody ApplicationHistoryDTO applicationHistoryDTO) {
		try {
			return new ResponseEntity<Object>(new Object() {
				public boolean result = applicationHistoryService.save(applicationHistoryDTO);
			}, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findBySeekerID1/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ApplicationHistoryDTO>> findBySeekerID1(@PathVariable("id") int id){
		try {
			return new ResponseEntity<List<ApplicationHistoryDTO>>(applicationHistoryService.findBySeekerID1(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ApplicationHistoryDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
