package com.hs.meetme.coursedetail.service;

import java.util.List;

import com.hs.meetme.coursedetail.domain.DetailVO;

public interface DetailService {
	public List<DetailVO> getCourse();
	
	public DetailVO courseOne(DetailVO vo); // 코스 상세보기
}
