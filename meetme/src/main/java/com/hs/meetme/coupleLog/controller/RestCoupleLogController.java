package com.hs.meetme.coupleLog.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;
import com.hs.meetme.coupleLog.service.CoupleLogService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/coupleLog/*")
public class RestCoupleLogController {
	@Autowired CoupleLogService cService;
	
	@GetMapping("/logList")
	public List<CoupleLogVO> getLogList(){
		return cService.getLogList();
	}
}
