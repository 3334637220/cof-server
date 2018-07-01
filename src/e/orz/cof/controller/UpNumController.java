package e.orz.cof.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import e.orz.cof.service.UpNumService;

@Controller
public class UpNumController {

	UpNumService upNumService = new UpNumService();

	@RequestMapping(value = "/updateLike.do")
	public void like(HttpServletRequest request, HttpServletResponse response) {

		String userName = request.getParameter("userName");
		int blogId = Integer.parseInt(request.getParameter("blogId"));

		System.out.println("like: " + userName + ", " + blogId);
		String msg;
		if (upNumService.checkUserLike(userName, blogId)) {
			if (upNumService.addUpNum(userName, blogId)) {
				msg = "点赞成功";
			} else {
				msg = "点赞失败";
			}
		} else {
			if (upNumService.minusUpNum(userName, blogId)) {
				msg = "取消赞成功";
			} else {
				msg = "取消赞失败";
			}
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/checkLike.do")
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
