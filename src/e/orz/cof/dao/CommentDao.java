package e.orz.cof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import e.orz.cof.model.Comment;

public class CommentDao {
	private Connection conn;

	public CommentDao() {
		conn = DB.getConnection();
	}

	public boolean addComment(String userName, String text) {
		String sql = "insert into blog_comment (userName, text) VALUES (?,?)";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, text);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteComment(int blogId) {
		String sql = "delete from blog_comment where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Comment> getCommentsById(int blogId) {
		ArrayList<Comment> comments = new ArrayList<>();
		String sql = "select * from blog_comment where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Comment comment = new Comment();
				comment.setBlogId(blogId);
				comment.setUserName(rs.getString(2));
				comment.setText(rs.getString(3));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}

}
