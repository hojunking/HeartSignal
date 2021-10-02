package com.hs.meetme.coursecreate.service;

import java.util.ArrayList;
import java.util.List;

import com.hs.meetme.coursecreate.domain.CourseCreateVO;

public interface CourseCreateService {
	
	int createCourse(CourseCreateVO vo);
	
	int createCourseOrder(ArrayList<String[]> list);
	
}
