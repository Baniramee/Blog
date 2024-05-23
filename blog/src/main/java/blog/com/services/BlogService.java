package blog.com.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import blog.com.models.dao.BlogDao;
import blog.com.models.entity.Blog;

@Service
public class BlogService {
	@Autowired
	private BlogDao blogDao;

	public List<Blog> selectAllBlogList(Long adminId) {
		if (adminId == null) {
			return null;
		} else {
			return blogDao.findAll();
		}
	}

	public boolean createBlog(String blogCategory, String blogArticle, String blogImage,
			String blogTitle, Long adminId) {
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogCategory, blogArticle, blogImage, blogTitle, adminId));
			return true;
		} else {
			return false;
		}

	}

	public Blog blogEditCheck(Long blogId) {
		if (blogId == null) {
			return null;
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}
	public boolean blogUpdate(Long blogId, String blogCategory, 
			String blogArticle, String blogImage,
			String blogTitle, Long adminId) {
		if (blogId == null) {
			return false;
		} else {
			Blog blog = blogDao.findByBlogId(blogId);
			blog.setBlogCategory(blogCategory);
			blog.setBlogArticle(blogArticle);
			blog.setBlogImage(blogImage);
			blog.setBlogTitle(blogTitle);
			blog.setBlogId(blogId);
			blogDao.save(blog);
			return true;
		}
	}
	public boolean deleteBlog(Long blogId) {
		if(blogId == null) {
			return false;
		}else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}

	
	
}


