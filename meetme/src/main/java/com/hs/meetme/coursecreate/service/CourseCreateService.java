package com.hs.meetme.coursecreate.service;

import java.util.ArrayList;
import java.util.List;

import com.hs.meetme.coursecreate.domain.CourseCreateVO;

public interface CourseCreateService {
	
	int createCourse(CourseCreateVO vo);
	
	// 커플용 추가
	int createCoupleCourse(CourseCreateVO vo);
	
	int createCourseOrder(ArrayList<String[]> list);

	int deleteCourseOrder(String courseId);
	int updateCourse(CourseCreateVO vo);
}
