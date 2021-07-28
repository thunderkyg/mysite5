package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	// AutoWired
	@Autowired
	private UserService userService;

	// LoginForm
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("[UserController.loginForm()]");

		return "/user/loginForm";
	}

	// Login
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login()]");

		UserVo authUser = userService.getUser(userVo);

		if (authUser != null) {
			System.out.println("로그인 성공함");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else {
			System.out.println("로그인 실패함");
			return "redirect:/user/loginForm?result=fail";
		}

	}

	// JoinForm
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("[UserController.joinForm]");

		return "/user/joinForm";
	}

	// Join
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(UserVo userVo) {
		System.out.println("[UserController.join]");

		userService.userInsert(userVo);

		return "/user/loginForm";
	}

	// Logout
	@RequestMapping(value = "/logout", method = { RequestMethod.GET, RequestMethod.POST })
	public String logout(HttpSession session) {

		// Remove Session
		session.removeAttribute("authUser");
		session.invalidate();

		return "redirect:/main";
	}

	// ModifyForm
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(HttpSession session, Model model) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		if(authUser != null) {
			UserVo userVo = userService.userInfo(authUser.getNo());
			model.addAttribute("userVo", userVo);
			
			return "/user/modifyForm";
		} else {
			
			return "/user/loginForm";
		}
		
	}
	
	// Modify
	@RequestMapping(value = "/modify", method = { RequestMethod.GET, RequestMethod.POST })
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		
		UserVo authUser = userService.userModify(userVo);
		
		((UserVo)session.getAttribute("authUser")).setName(authUser.getName());
		return "redirect:/main";
	}

}
