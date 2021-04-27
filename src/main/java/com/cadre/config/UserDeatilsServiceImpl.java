package com.cadre.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cadre.Dao.UserRepository;
import com.cadre.model.User;


public class UserDeatilsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//how to fetch user by database
		
		
		User user =  userRepository.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("username not found");
			
		}
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		
		return customUserDetails;
	}

}

