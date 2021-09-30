package com.hs.meetme.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.main.domain.MainVO;

@Mapper
public interface MainMapper {
	public List<MainVO> tagList();	// 태그 불러오기
	
	public List<MainVO> placeList(); // 장소 불러오기
	
	
	
	
}

