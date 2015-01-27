package hk.pazu.jba.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hk.pazu.jba.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/users")
@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public String removeUser(HttpServletRequest req, HttpServletResponse res) {
		Integer id = Integer.parseInt(req.getParameter("id"));
		userService.delete(id);
		return "redirect:/users.html";
	}

	@RequestMapping(value = "/remove/{id}")
	public String removeUserForm(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("action", "/users/remove/");
		return "redirect-form";
	}

}
