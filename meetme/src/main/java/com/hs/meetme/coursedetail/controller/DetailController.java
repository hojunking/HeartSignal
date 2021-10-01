package com.hs.meetme.coursedetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.coursedetail.domain.DetailVO;
import com.hs.meetme.coursedetail.mapper.DetailMapper;
import com.hs.meetme.coursedetail.service.DetailService;

@Controller
@RequestMapping("/*")
public class DetailController {
	@Autowired DetailMapper mapper;
	@Autowired DetailService service;
	
	@GetMapping("/CourseDetail")
	public String CourseDetail(Model model, DetailVO vo) {
		model.addAttribute("detail",service.getCourse());
		/* model.addAttribute("courseOne",service.courseOne(vo)); */
		return "main/courseDetail";
	}
	
	
}
