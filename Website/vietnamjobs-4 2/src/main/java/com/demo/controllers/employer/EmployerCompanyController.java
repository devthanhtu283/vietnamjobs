package com.demo.controllers.employer;

import com.demo.entities.Account;
import com.demo.entities.Employer;
import com.demo.helpers.FileHelper;
import com.demo.services.AccountService;
import com.demo.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping({"employer/company"})
public class EmployerCompanyController {

    @Autowired
    private EmployerService companyService;

    @Autowired
    private AccountService accountService;

    //ROUTES
    @GetMapping({"", "index", "/"})
    public String index(ModelMap map, Authentication auth) {
        Account account = accountService.getByUsername(auth.getName());
        map.put("company", companyService.getByAccountId(account.getId()));

        return "employer/company/index";

    }

    @GetMapping({"index/add", "/add"})
    public String add(ModelMap map, Authentication auth) {
        Account account = accountService.getByUsername(auth.getName());
        Employer company = companyService.getByAccountId(account.getId());


        Employer newCompany = new Employer();
        map.put("newCompany", newCompany);

        if (company != null) {
        map.put("company", companyService.getByAccountId(account.getId()));
            return "employer/company/index";
        } else {
            return "employer/company/add";
        }


    }

    @GetMapping({"index/update/{id}", "/update/{id}"})
    public String update(ModelMap map, @PathVariable("id") int id) {

        Employer company = companyService.getDetail(id);

        map.put("updateItem", company);

            return "employer/company/update";


    }

    //ACTIONS
    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Employer newItem,
                            RedirectAttributes redirectAttributes, Authentication auth) {

        try {
            Account account = accountService.getByUsername(auth.getName());
            newItem.setStatus(true);
            newItem.setAccount(account);
            newItem.setLogo("");
            newItem.setCover("");

            if (companyService.save(newItem)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
				return "redirect:/employer/company";
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
				return "redirect:/employer/company/add";
			}

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
			return "redirect:/employer/company/add";
        }

    }

    @PostMapping("handleUpdate")
    public String handleUpdate(@ModelAttribute("updateItem") Employer updateItem,
                            RedirectAttributes redirectAttributes, Authentication auth) {

        try {
        	updateItem.setStatus(true);
            if (companyService.save(updateItem)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
                return "redirect:/employer/company";
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
                return "redirect:/employer/company/update/"+updateItem.getId();
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
            return "redirect:/employer/company/update/"+updateItem.getId();
        }

    }

    @PostMapping("uploadLogo")
    public String uploadLogo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Authentication auth, ModelMap map) {
        Account account = accountService.getByUsername(auth.getName());
        Employer company = companyService.getByAccountId(account.getId());

        try {
            if (file.isEmpty()) {
                redirectAttributes.addFlashAttribute("notFound", "Failed");
                return "redirect:/employer/company/update/" + company.getId();
            }

            String contentType = file.getContentType();
            if (!contentType.startsWith("image")) {
                redirectAttributes.addFlashAttribute("notImage", "Failed");
                return "redirect:/employer/company/update/" + company.getId();
            }

            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();

            // Thư mục lưu trữ tệp
            File uploadFolder = new File("static/files");
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            File uploadFolder2 = new File("src/main/resources/static/assets/employer");
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }

            // Tạo tên tệp duy nhất
            String filename = FileHelper.generateFileName(originalFilename);

            // Tạo đường dẫn lưu trữ tệp
            Path path = Paths.get(uploadFolder.getAbsolutePath() + File.separator + filename);
            Path path2 = Paths.get(uploadFolder2.getAbsolutePath() + File.separator + filename);

            // Lưu tệp vào thư mục
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            Files.copy(file.getInputStream(), path2, StandardCopyOption.REPLACE_EXISTING);

            company.setLogo(filename);

            if (companyService.save(company)) {
                redirectAttributes.addFlashAttribute("success", "Success");
                map.put("updateItem", company);
                return "redirect:/employer/company/update/" + company.getId();
            } else {
                redirectAttributes.addFlashAttribute("error", "Failed");
                return "redirect:/employer/company/update/" + company.getId();
            }
        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Failed to upload the file: " + e.getMessage());
            return "redirect:/employer/company/update/" + company.getId();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/employer/company/update/" + company.getId();
        }
    }




}
