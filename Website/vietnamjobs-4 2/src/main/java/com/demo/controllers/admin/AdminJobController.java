package com.demo.controllers.admin;

import com.demo.entities.Postings;
import com.demo.services.EmployerService;
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
@RequestMapping("/admin/job")
public class AdminJobController {

    @Autowired
    private PostingService jobService;

    @Autowired
    private EmployerService companyService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        map.put("jobList", jobService.getAll());
        return "admin/job/index";
    }

    @GetMapping(value = {"index/{id}", "/{id}"})
    private String company(ModelMap map, @PathVariable("id") int id) {
        map.put("company", companyService.getDetail(id));
        map.put("jobList", jobService.getByEmployerId(id));
        return "admin/job/company";
    }

    @GetMapping("detail/{id}")
    public String details(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.put("item", jobService.getDetail(id));
        return "admin/job/detail";
    }

    //ACTIONS
    @PostMapping("updateStatus/{id}/{isDetail}")
    public String updateStatus(@PathVariable("id") int id, @PathVariable("isDetail") Boolean isDetail, RedirectAttributes redirectAttributes) {
        try {
            Postings job = jobService.find(id);

            if (job == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                if (isDetail) {
                    return "redirect:/admin/job/detail/" + id;
                } else {
                    return "redirect:/admin/job";
                }
            } else {
                boolean newStatus = !job.isStatus();
                job.setStatus(newStatus);

                if (jobService.updateStatusById(id, newStatus)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        if (isDetail) {
            return "redirect:/admin/job/detail/" + id;
        } else {
            return "redirect:/admin/job";
        }

    }

    @PostMapping("updateStatusFromCompany/{id}/{companyId}")
    public String updateStatusFromCompany(@PathVariable("id") int id, @PathVariable("companyId") int companyId, RedirectAttributes redirectAttributes) {
        try {
            Postings job = jobService.find(id);

            if (job == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/job/" + companyId;
            } else {
                boolean newStatus = !job.isStatus();
                job.setStatus(newStatus);

                if (jobService.updateStatusById(id, newStatus)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/job/" + companyId;

    }

}
