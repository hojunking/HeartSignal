package com.hs.meetme.coursecreate.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coursecreate.domain.PlaceVO;
import com.hs.meetme.coursecreate.domain.TagNumAddrYearVO;
import com.hs.meetme.coursecreate.domain.TensorCauseVO;
import com.hs.meetme.coursecreate.domain.TensorResultVO;
import com.hs.meetme.coursecreate.domain.TensorVO;
import com.hs.meetme.coursecreate.service.PlaceService;
import com.hs.meetme.coursecreate.service.TensorService;
import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/tensor/*")
public class RestTensorController {
	
	@Autowired TensorService tensorService;
	@Autowired PlaceService placeService;
	
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
	
	@GetMapping("/userTagAddrYear")
	public List<TagNumAddrYearVO> getTagAddrYear(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AccountVO userSession = (AccountVO) session.getAttribute("userSession");
		String userId = null;
		String addrzonecode = null;
		String birthYear = null;
		System.out.println(userSession.toString());
		if(userSession != null) {
			userId = userSession.getUserId();
			addrzonecode = userSession.getAddrzonecode();
			birthYear = userSession.getBirthYear();
		}
		
		if(addrzonecode.equals(null)) addrzonecode = "34501";		
		if(birthYear.equals(null)) birthYear = "1995";
		
		List<TagNumAddrYearVO> resultList = tensorService.getDataOfTensor(userId);
		
		if(resultList.isEmpty()) {
			TagNumAddrYearVO vo = new TagNumAddrYearVO();
			vo.setTagNum("20");
			vo.setAddrzonecode(addrzonecode);
			vo.setBirthYear(birthYear);
			resultList.add(vo);
		}
		
		return resultList;
	}
	
	@GetMapping("/getPlaceList")
	public List<PlaceVO> getPlaceList(String[] list) {
		List<PlaceVO> resultList = new ArrayList<PlaceVO>();
		
		for(String num : list) {
			PlaceVO vo = placeService.getPlaceById(num);
			int intNum = Integer.parseInt(num);
			if (vo == null) {
				while(vo == null) {
					num = String.valueOf(intNum);
					vo = placeService.getPlaceById(num);
					intNum = (int)(Math.random() * placeService.getPlaceAllCount() - 1);
				}
			}
			resultList.add(vo);
		}
		
		return resultList;
	}
}
