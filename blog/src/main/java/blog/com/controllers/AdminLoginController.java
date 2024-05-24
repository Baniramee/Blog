package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import blog.com.models.entity.Admin;
import blog.com.services.AdminService;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLoginController {
	
	@Autowired
	private AdminService adminService;

	// sessionインスタンスを導入
	@Autowired
	private HttpSession session;

	// ログインページを表示
	@GetMapping("/admin/login")
	public String getAdminLoginPage() {
		return "login.html";
	}
	// ログイン処理
	@PostMapping("/admin/login/process")
	public String adminLoginProcess(@RequestParam String adminEmail, @RequestParam String password) {
		// adminserviceのlogincheckメソッドを呼び出して、admin変数に格納
		Admin admin = adminService.loginCheck(adminEmail, password);
		// もし、adminがnullなら、もう一度ログイン画面を表示
		if (admin == null) {
			return "login.html";
		// そうでない場合、sessionにログイン情報を保存、
		// ブログ一覧画面にリダイレクト
		} else {
			session.setAttribute("loginAdminInfo", admin);
			return "redirect:/blog/list";
		}

	}

}
