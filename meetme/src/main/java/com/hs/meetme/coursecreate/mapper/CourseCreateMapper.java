package com.hs.meetme.coursecreate.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coursecreate.domain.CourseCreateVO;

@Mapper
public interface CourseCreateMapper {
	
	int createCourse(CourseCreateVO vo);
	
	// 커플용 추가
	int createCoupleCourse(CourseCreateVO vo);
	
	int createCourseOrder(ArrayList<String[]> list);
	
	int deleteCourseOrder(String courseId);
	int updateCourse(CourseCreateVO vo);
}
