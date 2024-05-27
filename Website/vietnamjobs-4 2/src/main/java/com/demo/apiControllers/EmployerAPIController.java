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
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.services.AccountService;
import com.demo.services.EmployerService;
import com.demo.services.PostingService;
import com.demo.services.SeekerService;


@Controller
@RequestMapping("api/employer")
public class EmployerAPIController {
	@Autowired
	private EmployerService employerService;
	
	@GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployerDTO>> findAll(){
		try {
			return new ResponseEntity<List<EmployerDTO>>(employerService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<EmployerDTO>>(HttpStatus.BAD_REQUEST);
		}
	}


	
	
}
