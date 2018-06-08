package com.etsisi.sps.services;

import com.etsisi.sps.entities.User;
import com.etsisi.sps.entities.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UserService userService;

    @Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		logger.info("User : {}", user);
		if(user==null){
			logger.info("User not found");
			throw new UsernameNotFoundException("Username not found");
		}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				 true, true, true, true, getGrantedAuthorities(user));
	}

	
	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for(UserRoles userRoles : user.getUserRoles()){
			logger.info("UserRoles : {}", userRoles);
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userRoles.getRole()));
		}
		logger.info("authorities : {}", authorities);
		return authorities;
	}
	
}
