package com.hs.meetme.useraccess.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/s/*")
public class SecurityController {
	
	@GetMapping("/all")
	public String all() {
		return "security/all";
	}
	
	@GetMapping("/member")
	public String member() {
		return "security/member";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "security/admin";
	}
	
	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
	
	@PostMapping("/login")
	public String loginProcess() {
		return "security/login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "security/logout";
	}
}
