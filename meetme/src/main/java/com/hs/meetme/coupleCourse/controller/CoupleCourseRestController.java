package com.hs.meetme.coupleCourse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;
import com.hs.meetme.coupleCourse.service.CoupleCourseService;

@RestController
@RequestMapping("/coupleCourse/*")
public class CoupleCourseRestController {
	@Autowired
	CoupleCourseService courseService;

	@DeleteMapping("/deleteCourse")
	@Transactional
	public boolean deleteCourse(@RequestBody CoupleCourseVO vo) {

		System.out.println(vo);
		
		courseService.deleteCourseOrder(vo);
		int r = courseService.deleteCourse(vo);
		return true;
	}
}
