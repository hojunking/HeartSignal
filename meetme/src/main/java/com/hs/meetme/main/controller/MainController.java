package com.hs.meetme.main.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.main.mapper.MainMapper;
import com.hs.meetme.main.service.MainService;

@Controller
@RequestMapping("/*")
public class MainController {
	@Autowired
	MainMapper mapper;
	@Autowired
	MainService service;

	// sample(원본 템플릿)
	@GetMapping("/index")
	public String index() {
		return "index";
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("tag",service.tagList());
		/* model.addAttribute("place",service.placeList()); */
		//model.addAttribute("course",service.courseList());
		model.addAttribute("address",service.addressList());
		/* model.addAttribute("regCourse",service.regionCourse()); */
		return "main/main";
	}
	

}
