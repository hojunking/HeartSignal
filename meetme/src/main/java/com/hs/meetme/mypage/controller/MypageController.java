package com.hs.meetme.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.domain.UserInfoVO2;
import com.hs.meetme.mypage.service.MypageService;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired MypageService mypageService;
	
	// 내 글 리스트 보기
	@GetMapping("/myinfo_my_write_list")
	public String myinfo_my_write_list(Model model, 
			                           @ModelAttribute("cri") Criteria cri) {
		
		int total = mypageService.getTotalCount(cri, 1);
		
		model.addAttribute("count",mypageService.getPostCount(1));
		
		model.addAttribute("list", mypageService.getPostList(cri, 1));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		
		return "mypage/myinfo_my_write_list";
	}
	
	// 내 댓글 리스트 보기
	@GetMapping("/myinfo_my_comment_list")
	public String myinfo_my_comment_list(Model model, 
			                             @ModelAttribute("cri") Criteria cri) {
		
		int total = mypageService.getTotalCommentCount(cri, 1);
		
		model.addAttribute("count",mypageService.getCommentCount(1));
		
		model.addAttribute("list", mypageService.getCommentList(cri, 1));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		
		return "mypage/myinfo_my_comment_list";
	}
	
	@GetMapping("/myinfo")
	public String myinfo(Model model, UserInfoVO2 userinfoVO) {
		
		userinfoVO.setUserId("1");
		
		model.addAttribute("userInfo", mypageService.getMyinfo(userinfoVO));
		
		return "mypage/myinfo";
	}
	
}
