package com.hs.meetme.coursecreate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagVO;

@Mapper
public interface PlaceMapper {
	
	List<PlaceVO> getList(); // 그냥 전체 리스트
	
	PlaceVO getPlace(String placeName);
	
	List<TagVO> getTagList();
	
	List<TagVO> getTagsSelected(int placeId); // 정한 장소의 태그들
	
	List<PlaceVO> getListBySearched(String[] keywords); // 검색 결과
	
}
