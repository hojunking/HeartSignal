package com.hs.meetme.useraccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hs.meetme.useraccess.domain.AccountVO;
import com.hs.meetme.useraccess.service.AccountServiceImpl;

@Controller
public class SecurityController {
	
	@Autowired
	AccountServiceImpl accountService;
	
	@GetMapping("/signUp")
	public String signUp() {
		return "security/signUp";
	}
	
	@PostMapping("/signUp")
	public String signUpPro(
			@ModelAttribute("account") AccountVO vo) {
		
		accountService.signUp(vo);
		return "security/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "security/login";
	}
}
