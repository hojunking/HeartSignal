package com.hs.meetme.coupleLog.controller;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;
import com.hs.meetme.coupleLog.service.CoupleLogService;
import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/coupleLog/*")
public class CoupleLogController {
	@Autowired
	CoupleLogService service;

	@GetMapping("/logList")
	public String getLogList(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String coupleId = ((AccountVO) session.getAttribute("userSession")).getCoupleId();
		model.addAttribute("logList", service.getLogList(coupleId));
		System.out.println(coupleId);
		return "coupleLog/coupleLogMain";
	}
	
	@GetMapping("/logOne")
	public String getLogOne(Model model,CoupleLogVO vo) {
		model.addAttribute("logList", service.getLog(vo));
		return "coupleLog/coupleLogOne";
	}
	

	@GetMapping("/coupleLogRecord")
	public void coupleLogRecord() {
		
	}

	@PostMapping("/coupleLogRecord")
	public String coupleLogRecord(CoupleLogVO vo, RedirectAttributes rttr, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String coupleId = ((AccountVO) session.getAttribute("userSession")).getCoupleId();
		vo.setCoupleId(Integer.valueOf(coupleId));
		service.logInsert(vo);
	
		System.out.println(vo);
		/*
		 * service.logInsertImg(vo); service.logInsertLogImg(vo);
		 */
		
		return "redirect:/coupleLog/logList";
	}

	@PostMapping("/coupleLogUpdate")
	public String copleLogUpdate(CoupleLogVO vo, RedirectAttributes rttr) {
		int result = service.logUpdate(vo);
		if (result == 1) {
			rttr.addAttribute("result", "success");
		}

		return "redirect:/coupleLog/logList";
	}
	
	@PostMapping("/coupleLogDelete")
	public String delete(CoupleLogVO vo, RedirectAttributes rttr) {
		int result = service.logDelete(vo);
		if (result == 1) {
			rttr.addFlashAttribute("result", "success");
		}

		return "redirect:/coupleLog/logList";
	}

}
