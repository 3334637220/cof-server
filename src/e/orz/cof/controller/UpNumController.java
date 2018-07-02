package e.orz.cof.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import e.orz.cof.service.BlogService;
import e.orz.cof.service.UpNumService;
import net.sf.json.JSONObject;

@Controller
public class UpNumController {

	UpNumService upNumService = new UpNumService();
	BlogService blogService = new BlogService();

	@RequestMapping(method = RequestMethod.POST, value = "/updateLike.do")
	public void like(HttpServletRequest request, HttpServletResponse response) {

		String userName = request.getParameter("userName");
		int blogId = Integer.parseInt(request.getParameter("blogId"));

		System.out.println("like: " + userName + ", " + blogId);
		String msg;
		JSONObject jo = new JSONObject();
		if (!upNumService.checkUserLike(userName, blogId)) {
			jo.put("type", "点赞");
			if (upNumService.addUpNum(userName, blogId)) {
				msg = "点赞成功";
				jo.put("status", "ok");
			} else {
				msg = "点赞失败";
				jo.put("status", "error");

			}
		} else {
			jo.put("type", "取消赞");
			if (upNumService.minusUpNum(userName, blogId)) {
				msg = "取消赞成功";
				jo.put("status", "ok");
			} else {
				msg = "取消赞失败";
				jo.put("status", "error");
			}
		}
		jo.put("upNum", blogService.getBlogById(blogId).getUpNum());
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(jo.toString());
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/checkLike.do")
	public void checkLike(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		int blogId = Integer.parseInt(request.getParameter("blogId"));

		System.out.println("checkLike: " + userName + ", " + blogId);

		String msg;
		if (upNumService.checkUserLike(userName, blogId)) {
			msg = "like";
		} else {
			msg = "dislike";
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
