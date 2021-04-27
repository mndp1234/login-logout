package com.cadre.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cadre.Dao.UserRepository;
import com.cadre.helper.Message;
import com.cadre.model.User;

@Controller
public class HomeController {
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepo;

	@RequestMapping("/")
	public String home(Model m) {
		m.addAttribute("title","Cadre");
		return "home";
	}
	
	
	@RequestMapping("/about")
	public String about(Model m) {
		m.addAttribute("title","Cadre");
		return "about";
	}
	
	@RequestMapping("/signup")
	public String signup(Model m) {
		m.addAttribute("title","Register");
		m.addAttribute("user",new User());
		return "signup";
	}
	
	@RequestMapping("/signin")
	public String loginn(Model m) {
		m.addAttribute("title","Login");
		return "signin";
	}
	
	@RequestMapping(value="/do_register", method = RequestMethod.POST)
	public String do_register(@ModelAttribute ("user") User user,Model m, HttpSession session) {
		
		try {
			System.out.println(user.toString());
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
			
			User result = this.userRepo.save(user);
			m.addAttribute("user",new User());
			
			session.setAttribute("Succesfully Registred", "alert-success");
			
			
			return "signin";
		}
		catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("user",user);
			session.setAttribute("message", new Message("Something went wrong"+ e.getMessage(),"alert-error"));
			return "signup";
		}
		
	}
	
	}
