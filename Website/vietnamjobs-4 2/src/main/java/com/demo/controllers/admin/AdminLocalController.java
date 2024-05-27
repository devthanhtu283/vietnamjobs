package com.demo.controllers.admin;

import com.demo.entities.Local;
import com.demo.services.LocalService;
import com.demo.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/local")
public class AdminLocalController {

    @Autowired
    private LocalService localService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Local newItem = new Local();
        Local updateItem = new Local();
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("localList", localService.findAll());
        return "admin/local/index";
    }

    //ACTIONS
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByLocalId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (localService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/admin/local";
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Local newItem,
                            RedirectAttributes redirectAttributes) {
        try {
            newItem.setStatus(true);

            if (localService.exists(newItem.getName(), 0)) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
                return "redirect:/admin/local";
            } else {
                if (localService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/admin/local";

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Local updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (localService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/local";
            }

            if (localService.exists(updateItem.getName(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (localService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/local";
    }


}
