package com.hs.meetme.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hs.meetme.sample.service.SampleService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class SampleController {
	
	@Autowired
	SampleService sampleService;
	
	@GetMapping("/")
	public String mypage(Model model) {
		model.addAttribute("sample", sampleService.getList());
		System.out.println(sampleService.getList());
		return "sample/sample";
	}
}
