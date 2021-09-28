package com.hs.meetme.mypage.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.UserInfoVO2;
import com.hs.meetme.mypage.service.MypageService;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired MypageService mypageService;
	
	@GetMapping("/myinfo_my_write_list")
	public String myinfo_my_write_list(Model model, @ModelAttribute("cri") Criteria cri) {

		
		int total = mypageService.getTotalCount(cri, 1);
		
		model.addAttribute("count",mypageService.getPostCount(cri, 1));
		model.addAttribute("list", mypageService.getPostList(cri, 1));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		
		return "mypage/myinfo_my_write_list";
	}
	
	@GetMapping("/myinfo")
	public String myinfo(Model model, UserInfoVO2 userinfoVO) {
		
		userinfoVO.setUserId(1);
		
		model.addAttribute("userInfo", mypageService.getMyinfo(userinfoVO));
		
		return "mypage/myinfo";
	}
	
}
