package com.hs.meetme.coupleCourse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;
import com.hs.meetme.coupleCourse.mapper.CoupleCourseMapper;

@Service
public class CoupleCourseServiceImpl implements CoupleCourseService {

	@Autowired CoupleCourseMapper courseMapper;
	@Override
	public List<CoupleCourseVO> getList(String coupleId) {
		// TODO Auto-generated method stub
		return courseMapper.getList(coupleId);
	}
	@Override
	public List<CoupleCourseVO> getCourse(String coupleId) {
		// TODO Auto-generated method stub
		return courseMapper.getCourse(coupleId);
	}
	@Override
	public int deleteCourse(CoupleCourseVO vo) {
		// TODO Auto-generated method stub
		return courseMapper.deleteCourse(vo);
	}
	@Override
	public int deleteCourseOrder(CoupleCourseVO vo) {
		// TODO Auto-generated method stub
		return courseMapper.deleteCourse(vo);
	}
	

}
