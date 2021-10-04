package com.hs.meetme.coupleinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;

@RestController

public class RestCoupleInfoController {
		@Autowired CoupleInfoService coupleService;
		
		
		@GetMapping("/matching/{id}")
		public String matchingUpdate(@PathVariable int id) {
			return null;
		}
		
		@GetMapping("/coupleInfo/{id}") //커플테이블정보와 나의 정보 불러오기
		public CoupleInfoVO coupleMainInfo(@PathVariable int id) {
			CoupleInfoVO vo = new CoupleInfoVO();
			vo.setUserId(id);
			vo =coupleService.coupleInfoSelect(vo);
			return vo;
		}
		@GetMapping("/myloveInfo") //내 여친정보 불러오기
		public CoupleInfoVO myLoveInfo(CoupleInfoVO vo) {
			if(vo.getCoupleStatus().equals("y")) {
			vo = coupleService.myLoverInfo(vo);
			}
			System.out.println(vo);
			return vo;
		}
		
		
}