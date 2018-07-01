package e.orz.cof.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import e.orz.cof.model.Blog;

public class BlogDao {
	private Connection conn;

	public BlogDao() {
		conn = DB.getConnection();
	}

	public boolean addBlog(String userName, String text) {
		String sql = "insert into blog VALUES (?,?)";
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

	public boolean deleteBlog(int blogId, String userName) {
		String sql = "delete from blog where blogId=? and userName=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ps.setString(2, userName);
			if (ps.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Blog> getAllBlog() {
		ArrayList<Blog> blogs = new ArrayList<>();
		String sql = "select * from blog";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setBlogId(rs.getInt(1));
				blog.setUserName(rs.getString(2));
				blog.setText(rs.getString(3));
				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogs;
	}

}