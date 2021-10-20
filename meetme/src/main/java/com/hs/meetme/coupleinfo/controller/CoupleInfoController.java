package com.hs.meetme.coupleinfo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;

@Controller

public class CoupleInfoController {
	@GetMapping("/coupleMain") //커플페이지 들어갈 때 경로
	public String NotCoupleMain(CoupleInfoVO vo) {
		String path="";
		if(vo.getCoupleStatus().equals("y")) {
			System.out.println("커플메인 입장");
			path="coupleMain/coupleMain";
		
		}else if(vo.getCoupleStatus().equals("w")) {
			System.out.println("커플이 없는 커플메인");
			path="coupleMain/coupleMain";
		}
		else if(vo.getCoupleStatus().equals("s")) {
			System.out.println("커플 신청중 커플메인");
			path="coupleMain/coupleMain";
		}
		else {
			System.out.println("결제메인 입장");
			path="coupleMain/notCouple";
		}
		return path;
	}
	
	@GetMapping("/coupleMain2")
	public String CoupleMain() {
		return "coupleMain/coupleMain";
	}
	
	@GetMapping("/coupleMain3")
	public String CouplePass() {
		return "coupleMain/notCouple";
	}
	
}
