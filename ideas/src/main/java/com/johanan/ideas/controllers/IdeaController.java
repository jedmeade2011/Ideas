package com.johanan.ideas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.johanan.ideas.models.Idea;
import com.johanan.ideas.models.User;
import com.johanan.ideas.service.GreatIdeaSerivce;
import com.johanan.ideas.service.UserService;
import com.johanan.ideas.validators.Validators;

@Controller
public class IdeaController {
	@Autowired
	private GreatIdeaSerivce iServe;
	@Autowired
	private UserService uServe;
	@Autowired
	private Validators usValid;
	
	@GetMapping("/")
	public String login(@ModelAttribute("user")User user) {
		return "login.jsp";
	}
	@PostMapping("/register/create")
	public String createUser(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		usValid.validate(user, result);
		if(result.hasErrors()) {
			return "login.jsp";
		}else {
			User newUser = this.uServe.registerThisUser(user);
			session.setAttribute("user_id", newUser.getId());
			return "redirect:/ideas";
		}
    }
	@PostMapping("/login/user")
	public String loginUser(@RequestParam("loginEmail")String email, @RequestParam("loginPass")String password, RedirectAttributes redirects, HttpSession sesh) {
		if(!this.uServe.authenticateUser(email, password)) {
			redirects.addFlashAttribute("loginError","Invalid Username or password.");
			return "redirect:/";
		}
		User user = this.uServe.getByEmail(email);
		sesh.setAttribute("user_id", user.getId());
		return "redirect:/ideas";
	}
	@GetMapping("/ideas")
	public String ideaPage(Model model, HttpSession session) {
		if(session.getAttribute("user_id")==null) {
			return "redirect:/";		
		}else {
			Long userId = (Long)session.getAttribute("user_id");
			User users = this.uServe.findOneId(userId);
			model.addAttribute("user", users);
			List<Idea> ideass = iServe.allIdeas();
			model.addAttribute("ideass", ideass);
			return "mainPage.jsp";
		}
	}
	@GetMapping("/like/{id}")
	public String likes(@PathVariable("id")Long id, HttpSession session) {
		Idea ideasLiked = this.iServe.getOneIdea(id);
		User liker = this.uServe.findOneId((Long)session.getAttribute("user_id"));
		this.iServe.likedUser(liker, ideasLiked);
		return "redirect:/ideas";
				
	}
	@GetMapping("/unlike/{id}")
	public String unliked(@PathVariable("id")Long id, HttpSession session) {
		Idea ideasLiked = this.iServe.getOneIdea(id);
		User liker = this.uServe.findOneId((Long)session.getAttribute("user_id"));
		this.iServe.unlikedUser(liker, ideasLiked);
		return "redirect:/ideas";
				
	}
	@GetMapping("/ideas/new")
	public String createIdea(@ModelAttribute("idea")Idea idea, HttpSession session) {
		if(session.getAttribute("user_id")==null) {
			return "redirect:/";		
		}else {
			return "newIdea.jsp";
		}
	}
	@PostMapping("create/idea")
	public String createEvent(@Valid @ModelAttribute("idea")Idea idea, BindingResult result, HttpSession session, Model model) {
		if(result.hasErrors()) {
			return "newIdea.jsp";
		}else {
			Long userId = (Long)session.getAttribute("user_id");
			User users = this.uServe.findOneId(userId);
			idea.setUses(users);
			this.iServe.createIdea(idea);
			return "redirect:/ideas";
		}
	}
	@GetMapping("/ideas/{id}")
	public String show(@PathVariable("id")Long id, Model model, HttpSession session) {
		if(session.getAttribute("user_id")==null) {
			return "redirect:/";		
		}else {
			Idea thisIdea = iServe.getOneIdea(id);
			User uses = this.uServe.findOneId((Long)session.getAttribute("user_id"));
			model.addAttribute("user", uses);
			model.addAttribute("ide", thisIdea);
			return "showIdea.jsp";
		}
	}
	@GetMapping("/ideas/{id}/edit")
	public String createIdea(@PathVariable("id")Long id, @ModelAttribute("idea")Idea idea, HttpSession session, Model model) {
		if(session.getAttribute("user_id")==null) {
			return "redirect:/";		
		}else {
			Idea editIdea = iServe.getOneIdea(id);
			model.addAttribute("idea", editIdea);
			return "edit.jsp";
		}
	}
	@PostMapping("/update/{id}")
	public String updateIdea(@Valid @PathVariable("id")Long id, @ModelAttribute("idea")Idea idea,HttpSession session, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			Long userId = (Long)session.getAttribute("user_id");
			User users = this.uServe.findOneId(userId);
			idea.setUses(users);
			this.iServe.updateIdea(idea);
			return "redirect:/ideas";
		}
	}
	@GetMapping("/delete/idea/{id}")
	public String deleteProduct(@PathVariable("id")Long id) {
			this.iServe.delete(id);
			return "redirect:/ideas";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
