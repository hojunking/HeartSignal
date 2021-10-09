package com.hs.meetme.coursedetail.service;

import java.util.List;

import com.hs.meetme.coursedetail.domain.DetailVO;

public interface DetailService {
	public List<DetailVO> getCourse(int courseId); // 코스 상세보기
	
//	public DetailVO courseOne(DetailVO vo); // 코스 상세보기
}
