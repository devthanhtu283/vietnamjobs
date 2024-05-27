package com.demo.apiControllers;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Account;
import com.demo.entities.Postings;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;
import com.demo.services.PostingService;
import com.demo.services.SeekerService;


@Controller
@RequestMapping("api/seeker")
public class SeekerAPIController {
	@Autowired
	private SeekerService seekerService;
	@Autowired
	private Environment environment;
	@GetMapping(value = "findByAccountID/{accountID}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<SeekerDTO> findByAccountID(@PathVariable("accountID") int accountID){
		try {
			return new ResponseEntity<SeekerDTO>(seekerService.findByAccountID(accountID), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<SeekerDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "update", consumes = MimeTypeUtils.APPLICATION_JSON_VALUE, 
			produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> update(@RequestBody SeekerDTO seekerDTO) {
			try {
				return new ResponseEntity<Object>(new Object() {
					public boolean result = seekerService.save(seekerDTO);
				}, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
			}
		}
	@PostMapping(value = "uploadCV", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) {
		try {
			// Kiểm tra xem tệp có rỗng không
			if (file.isEmpty()) {
				return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
			}

			// Kiểm tra xem tệp có phải là PDF không
			if (!file.getContentType().equals("application/pdf")) {
				return new ResponseEntity<>("Only PDF files are allowed", HttpStatus.BAD_REQUEST);
			}

			// Lấy thông tin của tệp
			String originalFilename = file.getOriginalFilename();
			String contentType = file.getContentType();
			long size = file.getSize();

			// Thư mục lưu trữ tệp
			File uploadFolder = new File(new ClassPathResource("static/files").getFile().getAbsolutePath());
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			// Tạo tên tệp duy nhất
			String filename = FileHelper.generateFileName(originalFilename); // hoặc sử dụng phương thức generateFileName

			// Tạo đường dẫn lưu trữ tệp
			Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);

			// Lưu tệp vào thư mục
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

			// Tạo URL cho tệp đã tải lên
			String fileUrl = environment.getProperty("FILES_URL") + filename;
			System.out.println(path);
			System.out.println(fileUrl);
			// Trả về URL của tệp đã tải lên
			return ResponseEntity.ok().body(new Object() {
				public String url = fileUrl;
			});
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
		}
	}
}
