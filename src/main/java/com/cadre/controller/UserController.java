package com.cadre.controller;

import java.security.Principal;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cadre.Dao.UserRepository;
import com.cadre.model.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;

	
	@ModelAttribute
	public void addCommondata(Model m,Principal principle) {
		String UserName = principle.getName();
		System.out.println("Username  " +UserName);
		//get the user data using the username which is email id
		
		User user = userRepository.getUserByUserName(UserName);
	    m.addAttribute("user",user);
	    m.addAttribute("title",user.getName().toUpperCase());
	}
	
	@RequestMapping("/index")
	public String dashboard(Model m,Principal principle) {
		
		return "normal/user_dashboard";
	}
	
	@RequestMapping("/view_profile")
	public String viewprofile(Model m,Principal principle) {
	    
		return "normal/view_profile";
	}
	
	@RequestMapping("/edit_profile")
	public String editprofile(Model m,Principal principal) {
		
		return "normal/edit_profile";
	}
	@RequestMapping("/do_save")
	public String save_profile(@ModelAttribute ("user") User user,Model m,Principal principal) {
		
		System.out.println(user);
		userRepository.save(user);
		return "redirect:/user/view_profile";
	}
	
	@RequestMapping("/show_people")
	public String show_people(Model m) {
		
		List<User> users = this.userRepository.findAll();
		m.addAttribute("show_users",users);
		return "show_people";
	}

}
