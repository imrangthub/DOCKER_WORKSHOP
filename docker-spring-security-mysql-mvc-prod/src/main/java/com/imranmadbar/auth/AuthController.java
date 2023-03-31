package com.imranmadbar.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	@GetMapping("/login")
	public String userLogin() {
		return "auth/loginPage";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "auth/accessDenied";
	}

}
