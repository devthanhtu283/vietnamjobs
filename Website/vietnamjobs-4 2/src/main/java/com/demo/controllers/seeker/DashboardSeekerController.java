package com.demo.controllers.seeker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"seeker/dashboard" })
public class DashboardSeekerController {

	@GetMapping({ "", "index", "/" })
	public String index() {
		return "seeker/dashboard/index";
	}

}
