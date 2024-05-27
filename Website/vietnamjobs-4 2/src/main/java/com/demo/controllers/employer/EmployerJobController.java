package com.demo.controllers.employer;

import com.demo.entities.Account;
import com.demo.entities.Category;
import com.demo.entities.Employer;
import com.demo.entities.Postings;
import com.demo.services.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
@RequestMapping({"employer/job"})
public class EmployerJobController {

    @Autowired
    private PostingService jobService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private EmployerService companyService;

    @Autowired
    private LocalService localService;

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private RankService rankService;

    @Autowired
    private WageService wageService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ApplicationHistoryService applicationHistoryService;

    //ROUTES
    @GetMapping({"", "index", "/"})
    public String index(ModelMap map, Authentication auth) {
        Account account = accountService.getByUsername(auth.getName());
        Employer company = companyService.getByAccountId(account.getId());
        map.put("jobList", jobService.getByEmployerId(company.getId()));

        return "employer/job/index";
    }

    @GetMapping({ "index/add", "/add"})
    public String add(ModelMap map, Authentication auth) {


        Postings newJob =  new Postings();
        map.put("newJob", newJob);

        map.put("localList", localService.findAllByStatus(true));
        map.put("typeList", typeService.findAllByStatus(true));
        map.put("experienceList", experienceService.findAllByStatus(true));
        map.put("rankList", rankService.findAllByStatus(true));
        map.put("wageList", wageService.findAllByStatus(true));
        map.put("categoryList", categoryService.findAllByStatus(true));

        return "employer/job/add";
    }

    @GetMapping({"index/update/{id}", "/update/{id}"})
    public String update(ModelMap map, @PathVariable("id") int id) {

        map.put("localList", localService.findAllByStatus(true));
        map.put("typeList", typeService.findAllByStatus(true));
        map.put("experienceList", experienceService.findAllByStatus(true));
        map.put("rankList", rankService.findAllByStatus(true));
        map.put("wageList", wageService.findAllByStatus(true));
        map.put("categoryList", categoryService.findAllByStatus(true));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        map.put("updateJob", jobService.getDetail(id));
//        map.put("updateDeadline", formatter.format(jobService.getDetail(id).getDeadline()));
//        map.put("updateCreated", formatter.format(jobService.getDetail(id).getCreated()));

        return "employer/job/update";
    }



    //ACTIONS
    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newJob") Postings newJob,
                            RedirectAttributes redirectAttributes, Authentication auth) {

        try {
            Account account = accountService.getByUsername(auth.getName());
            Employer company = companyService.getByAccountId(account.getId());

            newJob.setOpen(true);
            newJob.setStatus(false);
            newJob.setEmployer(company);
            newJob.setCreated(new Date());

            if (jobService.saveDB(newJob)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
                return "redirect:/employer/job";
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
                return "redirect:/employer/job/add";
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
            return "redirect:/employer/job/add";
        }
    }


    @PostMapping("handleUpdate")
    public String handleUpdate(@ModelAttribute("updateJob") Postings updateJob,
                               RedirectAttributes redirectAttributes, Authentication auth) {
        
        try {

            if (jobService.saveDB(updateJob)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");

            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");

            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/employer/job/update/" + updateJob.getId();
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if(applicationHistoryService.existByPostId(id)){
            redirectAttributes.addFlashAttribute("existApply", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (jobService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/employer/job";
    }





}
