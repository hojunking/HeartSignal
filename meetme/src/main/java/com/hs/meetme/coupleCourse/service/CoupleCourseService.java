package com.hs.meetme.coupleCourse.service;

import java.util.List;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;

public interface CoupleCourseService {
	public List<CoupleCourseVO> getList(String coupleId);

	public List<CoupleCourseVO> getCourse(String coupleId);

	public int deleteCourse(CoupleCourseVO vo);

}
