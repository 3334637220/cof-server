package e.orz.cof.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import e.orz.cof.model.Blog;
import e.orz.cof.service.BlogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BlogController {
	BlogService blogService = new BlogService();

	@RequestMapping(value = "/addBlog.do")
	public void addBlog(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String text = request.getParameter("text");
		String msg;
		if (blogService.addBlog(userName, text)) {
			msg = "发表成功";
		} else {
			msg = "发表失败";
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/delBlog.do")
	public void deleteBlog(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		String msg;
		if (blogService.deleteBlog(blogId, userName)) {
			msg = "删除成功";
		} else {
			msg = "删除失败";
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getBlogs.do")
	public void getBlogs(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<Blog> blogs = blogService.getAllBlog();
		JSONArray ja = new JSONArray();
		for (Blog b : blogs) {
			JSONObject jo = new JSONObject();
			jo.put("blogId", b.getBlogId());
			jo.put("userName", b.getUserName());
			jo.put("text", b.getText());
			ja.add(jo);
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
