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
		Admin admin = (Admin) session.getAttribute("loginAdminInfo");
		if (admin == null) {
			return "redirect:/admin/login";
		} else {
			model.addAttribute("adminName", admin.getAdminName());
			model.addAttribute("blog",new Blog());
			return "blog-create.html";
		}
	}
	

	@PostMapping("/blog/create/process")
	public String blogCreateProcess(@RequestParam String blogTitle, 
		@RequestParam String blogArticle,
		@RequestParam String blogCategory,
		@RequestParam MultipartFile blogImage){
			Admin admin = (Admin)session.getAttribute("loginAdminInfo");
			if (admin == null) {
				return "redirect:/admin/login";
		}else {
			String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-").format(new Date())+blogImage.getOriginalFilename();
			
			try {
				Files.copy(blogImage.getInputStream(), Path.of("src/main/resources/static/blog-img/"+fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(blogService.createBlog(blogTitle,blogCategory, blogArticle,  fileName, admin.getAdminId())) {
				return "redirect:/blog/list";
			}else {
				return "blog-create.html";
			}
			
		}
		
	}
}





