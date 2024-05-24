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
		// もし、管理者IDがnullの場合、nullを返す
		if (adminId == null) {
			return null;
			// そうでない場合、全てのブログを取得
		} else {
			return blogDao.findAll();
		}
	}

	
	public boolean createBlog(String blogCategory, String blogArticle, String blogImage, String blogTitle,
			Long adminId) {
		// もし、ブログタイトルがnull場合、新しいブログとして保存
		if (blogDao.findByBlogTitle(blogTitle) == null) {
			blogDao.save(new Blog(blogCategory, blogArticle, blogImage, blogTitle, adminId));
			// 新しいブログが正常に保存された場合、trueに返す
			return true;
			// そうでない場合、falseに返す
		} else {
			return false;
		}

	}

	public Blog blogEditCheck(Long blogId) {
		// もし、ブログIDがnull場合、nullに返す
		if (blogId == null) {
			return null;
			// そうでない場合、ブログIDに基づいてブログを検索
		} else {
			return blogDao.findByBlogId(blogId);
		}
	}

	public boolean blogUpdate(Long blogId, String blogCategory, String blogArticle, String blogImage, String blogTitle,
			Long adminId) {
		// もし、ブログIDがnull場合、falseに返す
		if (blogId == null) {
			return false;
			// そうでない場合、ブログの情報を更新
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
		// もし、ブログIDがnull場合、falseに返す
		if (blogId == null) {
			return false;
			// そうでない場合、ブログIDに基づいてブログを削除
		} else {
			blogDao.deleteByBlogId(blogId);
			return true;
		}
	}
}
