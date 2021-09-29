package com.hs.meetme.coursecreate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coursecreate.api.SearchPlaceDetailAPI;
import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagVO;
import com.hs.meetme.coursecreate.service.PlaceService;
import com.hs.meetme.sample.service.SampleService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/course/*")
public class RestCourseController {
	
	@Autowired PlaceService placeService; 
	
	@GetMapping("/place")
	public List<PlaceVO> getPlaceList() {
		return placeService.getList();
	}
	
	@GetMapping("/place/tags")
	public List<TagVO> getTagsAll() {
		return placeService.getTagList();
	}
	
	@GetMapping("/place/tags/{id}")
	public List<TagVO> getTagsBySelected(@PathVariable("id") int id) {
		log.info(id);
		return placeService.getTagsSelected(id);
	}
	
	// 검색 하기 (여러개받아서 할 수 있음.)
	@GetMapping("/place/search")
	public List<PlaceVO> getPlaceBySearched(String[] keywords) {
		for(String key : keywords) {
			log.info(key);
		}
		return placeService.getListBySearched(keywords);
	}
	
	// 장소 한개 가져오기
	@GetMapping("/place/searchOne/{placeName}")
	public PlaceVO getPlaceOne(@PathVariable("placeName") String placeName) {
		return placeService.getPlace(placeName);
	}
	
	// 네이버 장소 검색 (1개)
	@GetMapping("/place/searchOneNaver")
	public String getPlaceBySearched(String placeName) {
		String result = SearchPlaceDetailAPI.SearchPlaceByNaver(placeName);
		return result;
	}
	
	// 네이버 장소 이미지 검색 (1개)
	@GetMapping("/place/searchOneImage")
	public String getPlaceImageBySearched(String placeName) {
		String result = SearchPlaceDetailAPI.SearchPlaceImageByNaver(placeName);
		return result;
	}
	
}
