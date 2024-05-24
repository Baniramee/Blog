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
	
	// 登録画面の表示
	@GetMapping("/admin/register")
	public String getAdminRegisterPage() {
		// ロガーインスタンスを作成
		Logger logger = LoggerFactory.getLogger(AdminRegisterController.class);
		// 登録画面を表示したら、warnでメッセージを出力
		logger.warn("登録画面を表示しました");
		// 登録画面を表示
		return "register.html";
	}
	
	// 登録処理
	@PostMapping("/admin/register/process")
	public String adminRegisterProcess(
			@RequestParam String adminName,
			@RequestParam String adminEmail,
			@RequestParam String password) {
		// もし、createAdminがtrueだったら、ログイン画面を表示
		if (adminService.createAdmin(adminEmail, adminName, password)) {
			return "login.html";
			// そうでない場合、登録画面を表示
		} else {
			return "register.html";
		}

	}
}
