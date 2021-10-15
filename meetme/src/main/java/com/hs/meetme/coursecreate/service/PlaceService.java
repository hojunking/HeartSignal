package com.hs.meetme.coursecreate.service;

import java.util.List;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagVO2;

public interface PlaceService {
	
	List<PlaceVO> getList();
	
	PlaceVO getPlace(String placeName);
	
	PlaceVO getPlaceById(String placeId);
	
	List<TagVO2> getTagList();
	
	List<TagVO2> getTagsSelected(int placeId);
	
	List<PlaceVO> getListBySearched(String[] keywords); // 검색 결과

}
