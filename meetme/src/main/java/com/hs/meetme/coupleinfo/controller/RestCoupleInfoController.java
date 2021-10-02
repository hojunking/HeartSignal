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
		
		@GetMapping("/coupleImg") //커플이미지, d-day 발사
		public CoupleInfoVO coupleMainInfo(CoupleInfoVO vo) {
			vo =coupleService.coupleInfoSelect(vo);
			vo.getUserId();
			return vo;
		}
		@GetMapping("/mylove")
		public CoupleInfoVO myLoveInfo(CoupleInfoVO vo) {
			vo = coupleService.myLoverInfo(vo);
			System.out.println(vo);
			return vo;
		}
		
		
}