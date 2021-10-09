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

@Controller
@RequestMapping("/coupleLog/*")
public class CoupleLogController {
	@Autowired
	CoupleLogService service;

	@GetMapping("/logList")
	public String getLogList(Model model) {
		model.addAttribute("logList", service.getLogList());
		return "coupleLog/coupleLogMain";
	}

	@GetMapping("/coupleLogRecord")
	public String coupleLogRecord1(Model model) {
		model.addAttribute("logList", service.getLogList());
		return "coupleLog/coupleLogRecord";
	}

	@PostMapping("/coupleLogRecord")
	public String coupleLogRecord(CoupleLogVO vo, RedirectAttributes rttr) {

		return "coupleLog/coupleLogRecord";
	}

	@GetMapping("/coupleLogUpdate")
	public String copleLogUpdate() {

		return "coupleLog/coupleLogUpdate";
	}

}
