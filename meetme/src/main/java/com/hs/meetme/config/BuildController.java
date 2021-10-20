package com.hs.meetme.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hs.meetme.useraccess.domain.AccountVO;

@Controller
public class BuildController {
	
	@GetMapping("/vue")
	public String vueIndex() {
		return "index";
	}
	
	@GetMapping("/createCourse")
	public String createCourse(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountVO user = (AccountVO) session.getAttribute("userSession");
		System.out.println(user);
		
		if(user == null) {
			return "redirect:/login";
		}
		
		return "index";
	}
	
	@GetMapping("/createCourse/{placeId}")
	public String createCourse(HttpServletRequest request, @PathVariable String placeId) {
		HttpSession session = request.getSession();
		AccountVO user = (AccountVO) session.getAttribute("userSession");
		System.out.println(user);
		
		if(user == null) {
			return "redirect:/login";
		}
		
		return "index";
	}
	
	@GetMapping("/coupleCreateCourse")
	public String coupleCreateCourse(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountVO user = (AccountVO) session.getAttribute("userSession");
		
		// 조건을 좀 더 확인 해야함.
		if(user == null || user.getCoupleStatus().equals("n")) {
			return "redirect:/coupleMain?coupleStatus=n";
		}
		
		return "index";
	}
	
	@GetMapping("/updateCourse/{courseId}")
	public String updateCourse(HttpServletRequest request, @PathVariable String courseId) {
		HttpSession session = request.getSession();
		AccountVO user = (AccountVO) session.getAttribute("userSession");
		System.out.println(user);
		
		if(user == null) {
			return "redirect:/login";
		}
		
		return "index";
	}
	
	@GetMapping("/test")
	public String vueTest() {
		return "test";
	}
}
