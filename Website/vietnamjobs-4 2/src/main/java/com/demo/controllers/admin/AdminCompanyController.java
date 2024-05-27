package com.demo.controllers.admin;

import com.demo.entities.Employer;
import com.demo.services.EmployerService;
import com.demo.services.FollowService;
import com.demo.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController {

    @Autowired
    private EmployerService employerService;

    @Autowired
    private PostingService postingService;

    @Autowired
    private FollowService followService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        map.put("companyList", employerService.getAll());
        return "admin/company/index";
    }

    @GetMapping("detail/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("item", employerService.getDetail(id));
        modelMap.put("totalJob", postingService.countByEmployerId(id));
        modelMap.put("totalFollow", followService.countByEmployerId(id));
        return "admin/company/detail";
    }

    //ACTIONS
    @PostMapping("updateStatus/{id}")
    public String updateStatus(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            Employer employer = employerService.getDetail(id);

            if (employer == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/company";
            } else {
                boolean newStatus = !employer.isStatus();
                employer.setStatus(newStatus);

                if (employerService.updateStatusById(id, newStatus)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/company";
    }

}
