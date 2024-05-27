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
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.FollowDB;
import com.demo.dtos.FollowDTO;

import com.demo.services.FollowService;



@Controller
@RequestMapping("api/follow")
public class FollowAPIController {
	@Autowired
	private FollowService followService;
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping(value = "findBySeekerId/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FollowDTO>> findBySeekerId(@PathVariable("id") int id){
		try {
			return new ResponseEntity<List<FollowDTO>>(followService.findBySeekerId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FollowDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findBySeekerId1/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<FollowDTO> findByUsername(@PathVariable("id") int id){
		try {
			return new ResponseEntity<FollowDTO>(followService.findBySeekerId1(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<FollowDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "listPostFollowBySeekerID/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FollowDB>> listPostFollowBySeekerID(@PathVariable("id") int id){
		try {
			return new ResponseEntity<List<FollowDB>>(followService.listPostFollowBySeekerID(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<FollowDB>>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
