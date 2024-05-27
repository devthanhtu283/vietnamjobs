package com.demo.controllers.admin;

import com.demo.entities.Rank;
import com.demo.services.PostingService;
import com.demo.services.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/rank")
public class AdminRankController {

    @Autowired
    private RankService rankService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Rank newItem = new Rank();
        Rank updateItem = new Rank();
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("rankList", rankService.findAll());
        return "admin/rank/index";
    }

    //ACTIONS
    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByRankId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
        } else {
            if (rankService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        return "redirect:/admin/rank";
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Rank newItem,
                            RedirectAttributes redirectAttributes) {
        try {
            newItem.setStatus(true);

            if (rankService.exists(newItem.getName(), 0)) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
                return "redirect:/admin/rank";
            } else {
                if (rankService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }
        return "redirect:/admin/rank";

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Rank updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (rankService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/rank";
            }

            if (rankService.exists(updateItem.getName(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (rankService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/rank";
    }

}
