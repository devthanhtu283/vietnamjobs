package com.demo.controllers.admin;

import com.demo.entities.Category;
import com.demo.services.CategoryService;
import com.demo.services.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PostingService postingService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        Category newItem = new Category();
        Category updateItem = new Category();
        newItem.setParentId(0);
        updateItem.setParentId(0);
        map.put("newItem", newItem);
        map.put("updateItem", updateItem);
        map.put("categoryParentList", categoryService.getParent());
        return "admin/category/index";
    }

    @GetMapping(value = {"index/{id}", "/{id}"})
    private String children(ModelMap map, @PathVariable("id") int id) {
        Category newItem = new Category();
        Category updateItem = new Category();

        newItem.setParentId(id);
        updateItem.setParentId(id);

        map.put("newItem", newItem);
        map.put("updateItem", updateItem);

        map.put("parent", categoryService.find(id));
        map.put("categoryParentList", categoryService.getParent());
        map.put("categoryChildrenList", categoryService.getChildren(id));
        return "admin/category/children";
    }


    //ACTIONS
    @GetMapping("delete/{id}/{parentId}")
    public String delete(@PathVariable("id") int id, @PathVariable("parentId") int parentId, RedirectAttributes redirectAttributes) {

        if (postingService.existPostByCategoryId(id)) {
            redirectAttributes.addFlashAttribute("existPost", "Không thể xóa. Bạn có thể sửa trạng thái sang ẩn.");
            if (parentId > 0) {
                return "redirect:/admin/category/" + parentId;
            } else {
                return "redirect:/admin/category";
            }
        }

        if (categoryService.hasChildren(id)) {
            redirectAttributes.addFlashAttribute("hasChildren", "Có thư mục con");
        } else {
            if (categoryService.delete(id)) {
                redirectAttributes.addFlashAttribute("success", "Thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Thất bại...");
            }
        }

        if (parentId > 0) {
            return "redirect:/admin/category/" + parentId;
        } else {
            return "redirect:/admin/category";
        }
    }

    @PostMapping("handleAdd")
    public String handleAdd(@ModelAttribute("newItem") Category newItem,
                            RedirectAttributes redirectAttributes) {

        try {
            newItem.setStatus(true);

            if (categoryService.exists(newItem.getName(), newItem.getParentId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");

                if (newItem.getParentId() > 0) {
                    return "redirect:/admin/category/" + newItem.getParentId();
                } else {
                    return "redirect:/admin/category";
                }

            } else {
                if (categoryService.save(newItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");

        }

        if (newItem.getParentId() > 0) {
            return "redirect:/admin/category/" + newItem.getParentId();
        } else {
            return "redirect:/admin/category";
        }

    }

    @PostMapping("handleUpdate")
    public String update(@ModelAttribute("updateItem") Category updateItem,
                         RedirectAttributes redirectAttributes) {
        try {

            if (categoryService.find(updateItem.getId()) == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                if (updateItem.getParentId() > 0) {
                    return "redirect:/admin/category/" + updateItem.getParentId();
                } else {
                    return "redirect:/admin/category";
                }
            }

            if (categoryService.exists(updateItem.getName(), updateItem.getId())) {
                redirectAttributes.addFlashAttribute("exist", "Đã tồn tại.");
            } else {
                if (categoryService.save(updateItem)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        if (updateItem.getParentId() > 0) {
            return "redirect:/admin/category/" + updateItem.getParentId();
        } else {
            return "redirect:/admin/category";
        }
    }

}
