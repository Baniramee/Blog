package blog.com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import blog.com.models.entity.Admin;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogDeleteController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private HttpSession session;
	
	// 削除の処理
	@PostMapping("/blog/delete")
	public String blogDelete(Long blogId) {
		// sessionからログインしている人の情報をadminという変数に格納
		Admin admin = (Admin)session.getAttribute("loginAdminInfo");
		// もし、adminがnullなら、ログイン画面を表示
		if(admin == null) {
			return "redirect:/admin/login";
			// そうでない場合、ブログ削除が成功したかをチェック
		}else {
			if(blogService.deleteBlog(blogId)) {
				// 成功した場合、ブログ一覧画面にリダイレクト
				return "redirect:/blog/list";
				// そうでない場合、ブログ編集画面にリダイレクトい
			}else {
				return "redirect:/blog/editor"+blogId;
			}
		}
		
	}
	
}
