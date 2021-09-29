package com.hs.meetme.coursecreate.service;

import java.util.List;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagVO;

public interface PlaceService {
	
	List<PlaceVO> getList();
	
	List<TagVO> getTagList();
	List<TagVO> getTagsSelected(int placeId);
	
	List<PlaceVO> getListBySearched(String[] keywords); // 검색 결과
	
}
