package e.orz.cof.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import e.orz.cof.model.Blog;
import e.orz.cof.model.Comment;
import e.orz.cof.model.Picture;
import e.orz.cof.service.BlogService;
import e.orz.cof.service.CommentService;
import e.orz.cof.service.PictureService;
import e.orz.cof.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class BlogController {
	BlogService blogService = new BlogService();
	UserService userService = new UserService();
	CommentService commentService = new CommentService();
	PictureService pictureService = new PictureService();

	@RequestMapping(method = RequestMethod.POST, value = "/addBlog.do")
	public void addBlog(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String text = request.getParameter("text");
		String msg;
		int blogId = blogService.addBlog(userName, text);
		JSONObject jo = new JSONObject();
		if (blogId >= 0) {
			msg = "发表成功";
			jo.put("status", "ok");
			jo.put("blogId", blogId);

		} else {
			msg = "发表失败";
			jo.put("status", "error");
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(jo.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/delBlog.do")
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
			jo.put("upNum", b.getUpNum());
			jo.put("time", getDateDis(b.getTime(), Date.from(Instant.now())));
			jo.put("faceUrl", userService.getUser(b.getUserName()).getFaceUrl());

			JSONArray pictureJa = new JSONArray();
			for (Picture p : pictureService.getPicturesById(b.getBlogId())) {
				pictureJa.add(p.getImageUrl());
			}
			jo.put("pictures", pictureJa);

			JSONArray commentJa = new JSONArray();
			for (Comment c : commentService.getCommentsById(b.getBlogId())) {
				JSONObject commentJo = new JSONObject();
				commentJo.put("userName", c.getUserName());
				commentJo.put("text", c.getText());
				commentJa.add(commentJo);
			}
			jo.put("comments", commentJa);

			ja.add(jo);
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(ja.toString());
			System.out.println(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateDis(Date date, Date now) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的秒时间差异
		long diff = date.getTime() - now.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		StringBuilder sb = new StringBuilder();
		if (day > 0) {
			sb.append(day + "天");
		} else if (hour > 0) {
			sb.append(hour + "小时");
		} else if (min > 0) {
			sb.append(min + "分钟");
		}

		return sb.toString();
	}
}
