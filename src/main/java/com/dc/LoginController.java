package com.dc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(HttpServletRequest req, HttpSession session, Model model)
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String inputCode = req.getParameter("inputCode");

		String checkcode = (String) session.getAttribute("authCode");

		if (inputCode.equals(checkcode))
		{
			if ("admin".equalsIgnoreCase(username))
			{
				System.out.println(username + " login successfully");
				model.addAttribute("msg", "LoginSuccess!");
				return "loginSuccess";
			}
			else
			{
				model.addAttribute("msg", "username error!!");
				return "loginError";
			}
		}
		else
		{
			model.addAttribute("msg", "checkcode error!!");
			return "loginError";
		}

	}

}
