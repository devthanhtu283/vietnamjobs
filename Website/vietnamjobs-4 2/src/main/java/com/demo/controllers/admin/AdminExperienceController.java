package com.demo.controllers.admin;

import com.demo.entities.Experience;
import com.demo.services.ExperienceService;
import com.demo.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/experience")
public class AdminExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Experience newItem = new Experience();
        Experience updateItem = new Experience();
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("experienceList", experienceService.findAll());
        return "admin/experience/index";
    }

    //ACTIONS
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByExperienceId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (experienceService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/admin/experience";
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Experience newItem,
                            RedirectAttributes redirectAttributes) {
        try {
            newItem.setStatus(true);

            if (experienceService.exists(newItem.getName(), 0)) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
                return "redirect:/admin/experience";
            } else {
                if (experienceService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/admin/experience";

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Experience updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (experienceService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/experience";
            }

            if (experienceService.exists(updateItem.getName(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (experienceService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/experience";
    }

}
