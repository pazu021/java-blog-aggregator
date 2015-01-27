package hk.pazu.jba.controller;

import hk.pazu.jba.entity.Blog;
import hk.pazu.jba.entity.User;
import hk.pazu.jba.service.BlogService;
import hk.pazu.jba.service.UserService;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@ModelAttribute("blog")
	public Blog constructBlog() {
		return new Blog();
	}

	@RequestMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}

	@RequestMapping("/users/{id}")
	public String detail(Model model, @PathVariable int id) {
		model.addAttribute("user", userService.findOneWithBlogs(id));
		return "user-detail";
	}

	@RequestMapping(value = "/account")
	public String account(Model model, Principal principal) {
		String name = principal.getName();
		model.addAttribute("user", userService.findOneWithBlogs(name));
		return "user-account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public String doAddBlog(Model model,
			@Valid @ModelAttribute("blog") Blog blog, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return account(model, principal);
		}
		String name = principal.getName();
		blogService.save(blog, name);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/blog/remove", method = RequestMethod.POST)
	public String removeBlog(HttpServletRequest req, HttpServletResponse res) {
		Blog blog = blogService
				.findOne(Integer.parseInt(req.getParameter("id")));
		blogService.delete(blog);
		return "redirect:/account.html";
	}

	@RequestMapping(value = "/blog/remove/delete-blog/{id}")
	public String removeBlogForm(@PathVariable int id, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("action", "/blog/remove/");
		return "redirect-form";
	}

}
