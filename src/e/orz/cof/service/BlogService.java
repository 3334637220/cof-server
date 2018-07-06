package e.orz.cof.service;

import java.util.ArrayList;

import e.orz.cof.dao.BlogDao;
import e.orz.cof.model.Blog;

public class BlogService {
	private BlogDao blogDao;
	private PictureService pictureService = new PictureService();
	private CommentService commentService = new CommentService();

	public BlogService() {
		blogDao = new BlogDao();
	}

	public int addBlog(String userName, String text, String location) {
		return blogDao.addBlog(userName, text, location);
	}

	public boolean deleteBlog(int blogId, String userName) {
		if (blogDao.deleteBlog(blogId, userName)) {
			pictureService.deletePicture(blogId);
			commentService.deleteComment(blogId);
			return true;
		}
		return false;
	}

	public Blog getBlogById(int blogId) {
		return blogDao.getBlogById(blogId);
	}

	public ArrayList<Blog> getAllBlog() {
		return blogDao.getAllBlog();
	}
}
