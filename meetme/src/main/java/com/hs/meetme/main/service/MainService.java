package com.hs.meetme.main.service;

import java.util.List;

import com.hs.meetme.main.domain.MainVO;

public interface MainService {
	public List<MainVO> tagList(); // 태그 불러오기

	public List<MainVO> placeList(); // place_name 불러오기

}
