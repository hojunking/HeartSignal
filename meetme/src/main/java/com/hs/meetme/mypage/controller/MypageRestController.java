package com.hs.meetme.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.service.MypageService;

@RestController
@RequestMapping("/mypage/*")
public class MypageRestController {

	@Autowired MypageService mypageService;
	
	//수정하기 (주소)
	@PutMapping("/addressUpdate")
	public MyPageUserInfoVO addressUpdate(@RequestBody MyPageUserInfoVO vo) {
		
		mypageService.updateAddress(vo);
		mypageService.setDateUpdated(vo);
		
		return vo;
	}
}
