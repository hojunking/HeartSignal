package com.hs.meetme.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.main.domain.MainVO;

@Mapper
public interface MainMapper {
	public List<MainVO> tagList();	// 태그 불러오기
	
	public List<MainVO> placeList(int placeId); // 장소 불러오기
	
	public List<MainVO> courseList(String address); // 지역별 코스 리스트
	
	public List<MainVO> addressList();
	
	public List<MainVO> addressSec(String address); // 중구, 남구
	
	public List<MainVO> regionCourse(); // 해당 지역 누르면 나오는 코스
	
	//*************************************************//
	public List<MainVO> courseListReco(); // 코스 추천 리스트
	
	
}

