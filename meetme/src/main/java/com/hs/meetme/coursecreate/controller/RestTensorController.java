package com.hs.meetme.coursecreate.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coursecreate.domain.TensorCauseVO;
import com.hs.meetme.coursecreate.domain.TensorResultVO;
import com.hs.meetme.coursecreate.domain.TensorVO;
import com.hs.meetme.coursecreate.service.TensorService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/tensor/*")
public class RestTensorController {
	
	@Autowired TensorService tensorService;
	
	@GetMapping("/userCourseData")
	public List<TensorVO> getUserCourseData() {
		return tensorService.createTensorData();
	}
	
	// 원인 데이터
	@GetMapping("/userCourseCauseData")
	public List<int[]> getUserCourseCauseData() {
		
		List<TensorCauseVO> CauseList = tensorService.createTensorCauseData();
		List<int[]> sendList = new ArrayList<>();
		
		for (TensorCauseVO item : CauseList) {
			int[] arr = {
					item.getTagNum(),
					item.getAddress(),
					item.getBirthYear()
			};
			sendList.add(arr);
		}
		
		return sendList;
	}
	
	// 결과 데이터
	@GetMapping("/userCourseResultData")
	public List<int[]> getUserCourseResultData() {
		
		List<TensorResultVO> ResultList = tensorService.createTensorResultData();
		List<int[]> sendList = new ArrayList<>();
		
		for (TensorResultVO item : ResultList) {
			int[] arr = {
					item.getPlace1(),
					item.getPlace2(),
					item.getPlace3()
			};
			sendList.add(arr);
		}
		
		return sendList;
	}
	
}
