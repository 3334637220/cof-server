package e.orz.cof.service;

import java.util.ArrayList;

import e.orz.cof.dao.CommentDao;
import e.orz.cof.model.Comment;

public class CommentService {
	private CommentDao commentDao;

	public CommentService() {
		commentDao = new CommentDao();
	}

	public boolean addComment(int blogId, String userName, String text) {
		return commentDao.addComment(blogId, userName, text);
	}

	public boolean deleteComment(int blogId) {
		return commentDao.deleteComment(blogId);
	}

	public ArrayList<Comment> getCommentsById(int blogId) {
		return commentDao.getCommentsById(blogId);
	}
}
