package com.hs.meetme.coupleCourse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;
import com.hs.meetme.coupleCourse.mapper.CoupleCourseMapper;
import com.hs.meetme.coupleCourse.service.CoupleCourseService;
import com.hs.meetme.useraccess.domain.AccountVO;

@Controller
@RequestMapping("/coupleCourse/*")
public class CoupleCourseController {

	@Autowired CoupleCourseMapper mapper;
	@Autowired CoupleCourseService service;
	
	@GetMapping("/course")
	public String course(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String coupleId = ((AccountVO) session.getAttribute("userSession")).getCoupleId();
		
		model.addAttribute("list",service.getList(coupleId));
		model.addAttribute("detail",service.getCourse(coupleId));
		return "coupleCourse/coupleCourse";
	}
}
