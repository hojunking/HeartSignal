package com.hs.meetme.coupleinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;

@RestController

public class RestCoupleInfoController {
		@Autowired CoupleInfoService coupleService;
		public int received;
		public int request;
		@GetMapping("/matching/{id}")
		public String matchingUpdate(@PathVariable int id) {
			return null;
		}
		
		@GetMapping("/coupleImg") //커플이미지, d-day 발사
		public CoupleInfoVO coupleMainInfo(CoupleInfoVO vo) {
			vo =coupleService.coupleInfoSelect(vo);
			vo.getUserId();
			
			
			received=vo.getUserReceived();
			request=vo.getUserRequest();
			System.out.println(received+"휴1"+request);
			return vo;
		}
		@GetMapping("/getToken")
		public String getToken(String token) {
			RestTestAPI api =new RestTestAPI();
			/*
			 * RestTestAPI2 api2 =new RestTestAPI2(); System.out.println("시작합니다"); String
			 * token =api.getToken(); String result = api2.refund(token);
			 */
			return null;
		}
		
		
}