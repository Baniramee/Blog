package blog.com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import blog.com.models.entity.Admin;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogListController {
	@Autowired
	private HttpSession session;
	@Autowired
	private BlogService blogService;

	// ブログ一覧画面を表示
	@GetMapping("/blog/list")
	public String getBlogList(Model model) {
		// sessionからログインしている人の情報を取得
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、admin==null　ログイン画面にリダイレクト
		if (admin == null) {
			return "redirect:/admin/login";
			// そうでない場合、ログインしている人のidに基づいて、ブログリストを取得
		} else {
			List<Blog> blogList = blogService.selectAllBlogList(admin.getAdminId());
			// モデルにadminの名前を追加
			model.addAttribute("adminName", admin.getAdminName());
			// モデルにブログリストを追加
			model.addAttribute("blogList", blogList);
			// ブログ一覧画面を表示
			return "blog-list.html";
		}
	}
}
