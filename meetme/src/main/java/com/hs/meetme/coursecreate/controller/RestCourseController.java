package com.hs.meetme.coursecreate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.service.PlaceService;
import com.hs.meetme.sample.domain.TagVO;
import com.hs.meetme.sample.service.SampleService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/course/*")
public class RestCourseController {
	
	@Autowired PlaceService placeService; 
	@Autowired SampleService sampleService;
	
	@GetMapping("/place")
	public List<PlaceVO> getPlaceList() {
		return placeService.getList();
	}
	
	@GetMapping("/place/tags")
	public List<TagVO> getTagsAll() {
		return sampleService.getTagList();
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
	
}
