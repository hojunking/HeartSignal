package com.hs.meetme.coursecreate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.meetme.coursecreate.api.SearchPlaceDetailAPI;
import com.hs.meetme.coursecreate.domain.CourseCreateVO;
import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagVO;
import com.hs.meetme.coursecreate.service.CourseCreateService;
import com.hs.meetme.coursecreate.service.PlaceService;

import lombok.extern.log4j.Log4j2;

/**
 * @author 한재호
 */

@RestController
@Log4j2
@RequestMapping("/api/course/*")
public class RestCourseController {
	
	@Autowired PlaceService placeService; 
	@Autowired CourseCreateService courseCreateService; 
	
	/**
	 * @apiNote 장소 가져오는 메소드들
	 * @param placeName
	 * @return List
	 */
	
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
	
	// 검색 하기 (여러개 받아서 할 수 있음.)
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
	
	/**
	 * @apiNote 네이버 검색 api
	 * @param placeName
	 * @return naverApi place 1, images 1, 2~10
	 */
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
	
	// 네이버 장소 이미지 검색 (10개 2번째부터)
	@GetMapping("/place/searchOneImages")
	public String getPlaceImagesBySearched(String placeName) {
		String result = SearchPlaceDetailAPI.SearchPlaceImagesByNaver(placeName);
		return result;
	}
	
	
	// 코스 등록
	@PostMapping("/register")
	public String registerCourse(String courseName, String list) throws JsonProcessingException {
		System.out.println(list);
		CourseCreateVO vo = new CourseCreateVO();
		vo.setCourseName(courseName);
		
		courseCreateService.createCourse(vo);
		System.out.println(vo.getCourseId());
		
		String courseId = vo.getCourseId();
		
		
//		for (String[] inList : list) {
//			inList.add(courseId);
//		}
		
		//int resultNum = courseCreateService.createCourseOrder(list);
		
//		String result = "";
//		if(resultNum == 0) {
//			result = "error";
//		} else {
//			result = "success";
//		}
		
		return "";
	}
}
