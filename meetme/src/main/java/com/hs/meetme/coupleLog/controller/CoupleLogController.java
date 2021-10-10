package com.hs.meetme.coupleLog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;
import com.hs.meetme.coupleLog.service.CoupleLogService;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/coupleLog/*")
public class CoupleLogController {
	@Autowired
	CoupleLogService service;

	@GetMapping("/logList")
	public String getLogList(Model model) {
		model.addAttribute("logList", service.getLogList());
		return "coupleLog/coupleLogMain";
	}
	
	@GetMapping("/logOne")
	public String getLogOne(Model model,CoupleLogVO vo) {
		model.addAttribute("logOne", service.getLog(vo));
		return "coupleLog/coupleLogOne";
	}
	

	@GetMapping("/coupleLogRecord")
	public void coupleLogRecord() {
		
	}

	@PostMapping("/coupleLogRecord")
	public String coupleLogRecord(CoupleLogVO vo, RedirectAttributes rttr) {
		System.out.println(vo);
		service.logInsertImg(vo);
		System.out.println(vo);
		service.logInsert(vo);
		System.out.println(vo);
		/*
		 * service.logInsertImg(vo); service.logInsertLogImg(vo);
		 */
		
		return "redirect:/coupleLog/logList";
	}

	@GetMapping("/coupleLogUpdate")
	public String copleLogUpdate() {

		return "coupleLog/coupleLogUpdate";
	}

}
