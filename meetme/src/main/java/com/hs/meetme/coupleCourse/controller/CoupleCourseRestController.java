package com.hs.meetme.coupleCourse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;
import com.hs.meetme.coupleCourse.service.CoupleCourseService;
import com.hs.meetme.useraccess.domain.AccountVO;

@RestController
@RequestMapping("/coupleCourse/*")
public class CoupleCourseRestController {
	@Autowired
	CoupleCourseService courseService;

	@DeleteMapping("/deleteCourse")
	public boolean deleteCourse(@RequestBody CoupleCourseVO vo, HttpServletRequest request) {

		// 세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");

		int r = courseService.deleteCourse(vo);
		return r == 1 ? true : false;
	}
}
