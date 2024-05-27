package com.demo.apiControllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.dtos.PostingDTO;
import com.demo.entities.Postings;
import com.demo.services.PostingService;


@Controller
@RequestMapping("api/posting")
public class PostingAPIController {
	@Autowired
	private PostingService postingService;
	@GetMapping(value = "findAll", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostingDTO>> findAll(){
		try {
			return new ResponseEntity<List<PostingDTO>>(postingService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PostingDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findById/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<PostingDTO> findById(@PathVariable("id") int id){
		try {
			return new ResponseEntity<PostingDTO>(postingService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<PostingDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findByEmployerID/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostingDTO>> findByEmployerID(@PathVariable("id") int id){
		try {
			return new ResponseEntity<List<PostingDTO>>(postingService.findByEmployerId(id), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PostingDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping(value = "findbyemployertitle/{name}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PostingDTO>> findbyemployertitle(@PathVariable("name") String name){
		try {
			return new ResponseEntity<List<PostingDTO>>(postingService.findByTittle(name), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<PostingDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
}
