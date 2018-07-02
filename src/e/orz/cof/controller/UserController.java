package e.orz.cof.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import e.orz.cof.model.User;
import e.orz.cof.service.UserService;
import net.sf.json.JSONObject;

@Controller
public class UserController {

	UserService userService = new UserService();

	@RequestMapping(method = RequestMethod.POST, value = "/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		System.out.println("login: " + userName + ", " + password);
		String msg;
		JSONObject jo = new JSONObject();
		User user = userService.checkLogin(userName, password);
		if (user != null) {
			msg = "登录成功";
			jo.put("status", "ok");
			jo.put("userName", userName);
			jo.put("password", password);
			jo.put("faceUrl", user.getFaceUrl());

		} else {
			msg = "用户名或密码错误";
			jo.put("status", "error");
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(jo.toString());
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register.do")
	public void register(HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String faceUrl = request.getParameter("faceUrl");
		System.out.println("register: " + userName + ", " + password + ", " + faceUrl);
		String msg;

		if (userService.register(userName, password, faceUrl)) {
			msg = "注册成功";
		} else {
			msg = "用户名已存在";
		}
		try {
			response.setCharacterEncoding("utf8");
			response.getWriter().write(msg);
			System.out.println(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
