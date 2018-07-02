package e.orz.cof.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import e.orz.cof.model.Comment;
import e.orz.cof.service.CommentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
public class CommentController {
	CommentService commentService = new CommentService();

	@RequestMapping(method = RequestMethod.POST, value = "/addComment.do")
	public void addComment(HttpServletRequest request, HttpServletResponse response) {
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		String userName = request.getParameter("userName");
		String text = request.getParameter("text");
		String msg;
		if (commentService.addComment(blogId, userName, text)) {
			msg = "评论成功";
		} else {
			msg = "评论失败";
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/getComments.do")
	public void getComments(HttpServletRequest request, HttpServletResponse response) {
		int blogId = Integer.parseInt(request.getParameter("blogId"));
		ArrayList<Comment> comments = commentService.getCommentsById(blogId);
		JSONArray ja = new JSONArray();
		for (Comment c : comments) {
			JSONObject jo = new JSONObject();
			jo.put("blogId", c.getBlogId());
			jo.put("userName", c.getUserName());
			jo.put("text", c.getText());
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
