package com.hs.meetme.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BuildController {
	
	@GetMapping("/vue")
	public String vueIndex() {
		return "/index";
	}
	@GetMapping("/createCourse")
	public String createCourse() {
		return "/index";
	}
	@GetMapping("/coupleCreateCourse")
	public String coupleCreateCourse() {
		return "/index";
	}
}
