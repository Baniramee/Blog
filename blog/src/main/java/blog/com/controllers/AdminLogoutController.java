package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminLogoutController {

	// sessionを導入
	@Autowired
	private HttpSession session;

	//　ログアウトに処理
	@GetMapping("/admin/logout")
	public String adminLogout() {
		// sessionを無効化
		session.invalidate();
		// ログアウトした後、ログイン画面にリダイレクト
		return "redirect:/admin/login";
	}
}
