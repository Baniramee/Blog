package blog.com.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import blog.com.services.AdminService;

@Controller
public class AdminRegisterController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {

		Logger logger = LoggerFactory.getLogger(AdminRegisterController.class);
		logger.warn("登録画面を表示しました");
		return "register.html";
	}

	@PostMapping("/admin/register/process")
	public String adminRegisterProcess(@RequestParam String adminName,
			@RequestParam String adminEmail,
			@RequestParam String password) {

		if (adminService.createAdmin(adminEmail, adminName, password)) {
			return "login.html";
		} else {
			return "register.html";
		}

	}
}
