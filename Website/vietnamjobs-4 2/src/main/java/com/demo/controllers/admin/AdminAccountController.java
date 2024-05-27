package com.demo.controllers.admin;

import com.demo.entities.Account;
import com.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountController {

    @Autowired
    private AccountService accountService;

    //ROUTES
    @GetMapping(value = {"index", "", "/"})
    private String index(ModelMap map) {
        map.put("accountList", accountService.getAll());
        return "admin/account/index";
    }

    //ACTIONS
    @PostMapping("updateStatus/{id}")
    public String updateStatus(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        try {
            Account account = accountService.find(id);

            if (account == null) {
                redirectAttributes.addFlashAttribute("notFound", "Không tìm thấy...");
                return "redirect:/admin/account";
            } else {
                boolean newStatus = !account.isStatus();
                account.setStatus(newStatus);

                if (accountService.updateStatusById(id, newStatus)) {
                    redirectAttributes.addFlashAttribute("success", "Thành công!");
                } else {
                    redirectAttributes.addFlashAttribute("error", "Thất bại...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Thất bại...");
        }

        return "redirect:/admin/account";
    }


}
