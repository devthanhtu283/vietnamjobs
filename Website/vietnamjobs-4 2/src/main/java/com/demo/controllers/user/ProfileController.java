package com.demo.controllers.user;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.demo.dtos.AccountDTO;
import com.demo.dtos.ApplicationHistoryDTO;
import com.demo.dtos.EmployerDTO;
import com.demo.dtos.PostingDTO;
import com.demo.dtos.SeekerDTO;
import com.demo.entities.Account;
import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.Seeker;
import com.demo.entities.TypeAccount;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;
import com.demo.services.AdminService;
import com.demo.services.ApplicationHistoryService;
import com.demo.services.EmployerService;
import com.demo.services.MailService;
import com.demo.services.PostingService;
import com.demo.services.SeekerService;
import com.demo.services.TypeAccountService;

@Controller
@RequestMapping("seeker/profile")
public class ProfileController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private SeekerService seekerService;
	@Autowired
	private ApplicationHistoryService applicationHistoryService;
	@Autowired
	private PostingService postingService;
	@Autowired
	private EmployerService employerService;
	
	@GetMapping({ "infor"})
	public String index(ModelMap modelmap, Authentication authentication) {	
	
			SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());
			modelmap.put("seeker", seeker);
		
	
		return "user/profile/index";
	}
	
	@GetMapping("employer")
	public String employer(ModelMap modelMap, Authentication authentication) {
		SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());
		List<ApplicationHistoryDTO> applicationHistorys =  applicationHistoryService.findByStatusAndResult(seeker.getId(), 2, 1);
		List<EmployerDTO> employers = new ArrayList<>();
		for(ApplicationHistoryDTO applicationHistory : applicationHistorys) {
			PostingDTO posting = postingService.findById(applicationHistory.getPostingID());
			System.out.print(posting.getEmployerName());
			EmployerDTO employer = employerService.findbyname(posting.getEmployerName());
			System.out.print(employer.getName());

			if (employer != null) {
				employers.add(employer);
		    }
		}
		modelMap.put("employers", employers);
		modelMap.put("randomPostings", postingService.findAllLimit(10));
		return "user/profile/employer";
	}

	@GetMapping("postingapplied")
	public String postingapplied(ModelMap modelMap, Authentication authentication) {
		SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());
		modelMap.put("seekerId", seeker.getId());
		List<ApplicationHistoryDTO> applicationHistorys = applicationHistoryService.findAppliedCV(seeker.getId());
		List<PostingDTO> postings = new ArrayList<>();
		for(ApplicationHistoryDTO applicationHistory : applicationHistorys) {
			PostingDTO posting = postingService.findById(applicationHistory.getPostingID());
			if (posting != null) {
		        postings.add(posting);
		    }
		}
		modelMap.put("postings", postings);
		modelMap.put("randomPostings", postingService.findAllLimit(10));
		return "user/profile/postingapplied";
	}
	
	@GetMapping("postingsaved")
	public String postingsaved(ModelMap modelMap, Authentication authentication) {
		SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(authentication.getName()).getId());
		List<ApplicationHistoryDTO> applicationHistorys = applicationHistoryService.findByStatusAndResult(seeker.getId(), 2, 0);
		List<PostingDTO> postings = new ArrayList<>();
		for(ApplicationHistoryDTO applicationHistory : applicationHistorys) {
			PostingDTO posting = postingService.findById(applicationHistory.getPostingID());
			if (posting != null) {
		        postings.add(posting);
		    }
		}
		modelMap.put("postings", postings);
		modelMap.put("randomPostings", postingService.findAllLimit(10));
		return "user/profile/postingsaved";
	}
	
	@PostMapping("uploadAvatar")
    public String uploadLogo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Authentication auth, @RequestParam("fullname") String fullname
    		, @RequestParam("phone") String phone) {
       
		try {
		    AccountDTO account = accountService.findByUsername(auth.getName());
		    SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(auth.getName()).getId());
		    seeker.setFullName(fullname);
		    seeker.setPhone(phone);

		    // Kiểm tra xem người dùng đã chọn file hay không
		    if (file != null && !file.isEmpty() && file.getSize() > 0) {
		        String contentType = file.getContentType();
		        String originalFilename = file.getOriginalFilename();
		        long size = file.getSize();

		        // Thực hiện lưu file
		        // Thư mục lưu trữ tệp
		        File uploadFolder = new File("static/files");
		        if (!uploadFolder.exists()) {
		            uploadFolder.mkdirs();
		        }

		        // Tạo tên tệp duy nhất
		        String filename = FileHelper.generateFileName(originalFilename);

		        // Tạo đường dẫn lưu trữ tệp
		        Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);

		        // Lưu tệp vào thư mục
		        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

		        seeker.setAvatar(filename);
		    }

		    // Lưu thông tin người dùng
		    if (seekerService.save(seeker)) {
		        System.out.println("success");
		        redirectAttributes.addFlashAttribute("success", "Success");
		        return "redirect:/seeker/profile/infor";
		    } else {
		        System.out.println("failed");
		        redirectAttributes.addFlashAttribute("error", "Failed");
		        return "redirect:/seeker/profile/infor";
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		    redirectAttributes.addFlashAttribute("error", "Failed");
		    return "redirect:/seeker/profile/infor";
		}

    }
	
	@PostMapping(value = "uploadCV")
	public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Authentication auth) {
		 AccountDTO account = accountService.findByUsername(auth.getName());
		   SeekerDTO seeker = seekerService.findByAccountID(accountService.findByUsername(auth.getName()).getId());
		try {
			// Kiểm tra xem tệp có rỗng không
			if (file.isEmpty()) {
				   redirectAttributes.addFlashAttribute("error", "Failed");
				    return "redirect:/seeker/profile/infor";
			}

			// Kiểm tra xem tệp có phải là PDF không
			if (!file.getContentType().equals("application/pdf")) {
				   redirectAttributes.addFlashAttribute("error", "Failed");
				    return "redirect:/seeker/profile/infor";
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
			seeker.setCvInformation(filename);
			  if (seekerService.save(seeker)) {
			        System.out.println("success");
			        redirectAttributes.addFlashAttribute("success", "Success");
			        return "redirect:/seeker/profile/infor";
			    } else {
			        System.out.println("failed");
			        redirectAttributes.addFlashAttribute("error", "Failed");
			        return "redirect:/seeker/profile/infor";
			    }
		} catch (Exception e) {
			e.printStackTrace();
		    redirectAttributes.addFlashAttribute("error", "Failed");
		    return "redirect:/seeker/profile/infor";
		}
	}

}
