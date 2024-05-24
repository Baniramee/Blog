package blog.com.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import blog.com.models.entity.Admin;
import blog.com.models.entity.Blog;
import blog.com.services.BlogService;
import jakarta.servlet.http.HttpSession;

@Controller
public class BlogCreateController {
	@Autowired
	private BlogService blogService;

	@Autowired
	private HttpSession session;

	@GetMapping("/blog/create")
	public String getBlogCreatePage(Model model) {
		// sessionからログインしている情報をadminという変数に格納
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		// もし、adminがnullなら、ログイン画面にリダイレクト
		if (admin == null) {
			return "redirect:/admin/login";
			// そうでない場合、ログインしている人の名前をモデルに保存
		} else {
			model.addAttribute("adminName", admin.getAdminName());
			// モデルに新しいブログを追加、ブログ作成画面を表示
			model.addAttribute("blog",new Blog());
			return "blog-create.html";
		}
	}
	
	// ブログの作成処理
	@PostMapping("/blog/create/process")
	// リクエストパラメータとしてブログの情報を取得
	public String blogCreateProcess(@RequestParam String blogTitle, 
		@RequestParam String blogArticle,
		@RequestParam String blogCategory,
		@RequestParam MultipartFile blogImage){
		// sessionからログインしている人の情報をadminという変数に格納
			Admin admin = (Admin)session.getAttribute("loginAdminInfo");
			// もし、adminがnullなら、ログイン画面にリダイレクト
			if (admin == null) {
				return "redirect:/admin/login";
				// そうでない場合、画像のファイル名を生成
		}else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
			// 画像ファイルを保存
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				// エラーを出力
				e.printStackTrace();
			}
			// もしブログの作成が成功した場合、ブログ一覧画面にリダイレクト
			if(blogService.createBlog(blogTitle,blogCategory, blogArticle,  fileName, admin.getAdminId())) {
				return "redirect:/blog/list";
				// そうでない場合、ブログ作成画面に表示
			}else {
				return "blog-create.html";
			}
			
		}
		
	}
}





