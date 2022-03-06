package com.viola.backend.voilabackend;

import com.viola.backend.voilabackend.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/")
	public String index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

   		System.out.println("--------------------------------------------------------------");
		User user = (User) auth.getPrincipal();
		
		//Get the username of the logged in user: getPrincipal()
		System.out.println("auth.getPrincipal()=>"+user.getUsername() );
		//Get the username of the logged in user: getPrincipal()
		System.out.println("auth.getPrincipal().getId()=>"+user.getId() );
		//Get the password of the authenticated user: getCredentials()
		System.out.println("auth.getCredentials()=>"+auth.getCredentials());
		//Get the assigned roles of the authenticated user: getAuthorities()
		System.out.println("auth.getAuthorities()=>"+auth.getAuthorities());
		//Get further details of the authenticated user: getDetails()
		System.out.println("auth.getDetails()=>"+auth.getDetails());
		System.out.println("--------------------------------------------------------------");
		return user.getName() + ", Greetings from Spring Boot!";
	}

}
