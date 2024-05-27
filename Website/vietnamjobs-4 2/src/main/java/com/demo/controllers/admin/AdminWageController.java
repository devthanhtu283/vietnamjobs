package com.demo.controllers.admin;

import com.demo.entities.Wage;
import com.demo.services.PostingService;
import com.demo.services.WageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/wage")
public class AdminWageController {

    @Autowired
    private WageService wageService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Wage newItem = new Wage();
        Wage updateItem = new Wage();
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("wageList", wageService.findAll());
        return "admin/wage/index";
    }

    //ACTIONS
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByWageId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (wageService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/admin/wage";
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Wage newItem,
                            RedirectAttributes redirectAttributes) {
        try {
            newItem.setStatus(true);

            if (wageService.exists(newItem.getMin(), newItem.getMax(), 0)) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
                return "redirect:/admin/wage";
            } else {
                if (wageService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/admin/wage";

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Wage updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (wageService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/wage";
            }

            if (wageService.exists(updateItem.getMin(), updateItem.getMax(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (wageService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/wage";
    }

}
