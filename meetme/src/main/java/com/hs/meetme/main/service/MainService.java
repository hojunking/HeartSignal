package com.hs.meetme.main.service;

import java.util.List;

import com.hs.meetme.main.domain.MainVO;

public interface MainService {
	public List<MainVO> tagList(); // 태그 불러오기

	public List<MainVO> placeList(); // place_name 불러오기

	public List<MainVO> courseList(String address); // 지역별 코스 리스트
	
	public List<MainVO> addressList(); // 인천, 서울
	
	public List<MainVO> addressSec(String address); // 중구, 남구
	
	public List<MainVO> regionCourse(); // 해당 지역 누르면 나오는 코스
	
}
