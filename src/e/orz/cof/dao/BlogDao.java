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

	public int addBlog(String userName, String text, String location) {
		String sql = "insert into blog(userName, text, location) VALUES (?,?,?);";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, text);
			ps.setString(3, location);
			ps.executeUpdate();
			ps = (PreparedStatement) conn.prepareStatement("select LAST_INSERT_ID()");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
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

	public Blog getBlogById(int blogId) {
		String sql = "select * from blog where blogId=?";
		PreparedStatement ps;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, blogId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Blog blog = new Blog();
				blog.setBlogId(rs.getInt(1));
				blog.setUserName(rs.getString(2));
				blog.setText(rs.getString(3));
				blog.setUpNum(rs.getInt(4));
				blog.setTime(rs.getTimestamp(5));
				blog.setLocation(rs.getString(6));
				return blog;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
				blog.setUpNum(rs.getInt(4));
				blog.setTime(rs.getTimestamp(5));
				blog.setLocation(rs.getString(6));
				blogs.add(blog);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return blogs;
	}

}
