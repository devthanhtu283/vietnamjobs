package com.demo.controllers.admin;

import com.demo.entities.Type;
import com.demo.services.PostingService;
import com.demo.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/type")
public class AdminTypeController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Type newItem = new Type();
        Type updateItem = new Type();
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("typeList", typeService.findAll());
        return "admin/type/index";
    }

    //ACTIONS
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByTypeId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (typeService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/admin/type";
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Type newItem,
                            RedirectAttributes redirectAttributes) {
        try {
            newItem.setStatus(true);

            if (typeService.exists(newItem.getName(), 0)) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
                return "redirect:/admin/type";
            } else {
                if (typeService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/admin/type";

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Type updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (typeService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/type";
            }

            if (typeService.exists(updateItem.getName(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (typeService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/type";
    }

}
