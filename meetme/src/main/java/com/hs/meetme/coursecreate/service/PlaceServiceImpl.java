package com.hs.meetme.coursecreate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.mapper.PlaceMapper;
import com.hs.meetme.sample.domain.TagVO;
import com.hs.meetme.sample.mapper.SampleMapper;

@Service
public class PlaceServiceImpl implements PlaceService {
	
	@Autowired PlaceMapper placeMapper;
	
	// 모든 장소 출력
	@Override
	public List<PlaceVO> getList() {
		return placeMapper.getList();
	}
	
	// 지정한 장소의 태그들 출력
	@Override
	public List<TagVO> getTagsSelected(int placeId) {
		return placeMapper.getTagsSelected(placeId);
	}

	@Override
	public List<PlaceVO> getListBySearched(String[] keywords) {
		return placeMapper.getListBySearched(keywords);
	}

}
