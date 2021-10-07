package com.hs.meetme.coupleinfo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;

@Controller

public class CoupleInfoController {
	@GetMapping("/coupleMain")
	public String NotCoupleMain(CoupleInfoVO vo) {
		String path="";
		if(vo.getCoupleStatus().equals("y")) {
			System.out.println("커플메인 입장");
			path="coupleMain/coupleMain";
		
		}else {
			System.out.println("결제메인 입장");
			path="coupleMain/notCouple";
		}
		return path;
	}
	@GetMapping("/coupleMain2")
	public String CoupleMain() {
		return "coupleMain/coupleMain";
	}
	
}
