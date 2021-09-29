package com.hs.meetme.coupleinfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class CoupleInfoController {
	@GetMapping("/coupleMain")
	public String NotCoupleMain() {
		return "coupleMain/notCouple";
	}
	
	@GetMapping("/coupleMain2")
	public String CoupleMain() {
		return "coupleMain/coupleMain";
	}
}
