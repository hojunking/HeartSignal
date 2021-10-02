package com.hs.meetme.coursedetail.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coursedetail.domain.DetailVO;

@Mapper
public interface DetailMapper {
	public List<DetailVO> getCourse();
	
	public DetailVO courseOne(DetailVO vo); // 지역 선택되면 코스 상세보기
}
