package com.hs.meetme.coursecreate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coursecreate.domain.CourseCreateVO;
import com.hs.meetme.coursecreate.mapper.CourseCreateMapper;

@Service
public class CourseCreateServiceImpl implements CourseCreateService {

	@Autowired CourseCreateMapper courseCreateMapper;
	
	@Override
	public int createCourse(CourseCreateVO vo) {
		return courseCreateMapper.createCourse(vo);
	}

	@Override
	public int createCourseOrder(ArrayList<String[]> list) {
		return courseCreateMapper.createCourseOrder(list);
	}

}
