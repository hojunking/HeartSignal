package com.hs.meetme.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.useraccess.domain.AccountVO;

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
	
	// 유저 인포 보기
	@GetMapping("/myinfo")
	public String myinfo(Model model, HttpServletRequest request, MyPageUserInfoVO myPageUserInfoVO) {
		
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		
		if(accountVO==null) {
			return "mypage/myinfo";
		}else {
		  myPageUserInfoVO.setUserId(accountVO.getUserId());
		}
		
		model.addAttribute("userInfo", mypageService.getMyinfo(myPageUserInfoVO));
		
		return "mypage/myinfo";
	}
	
}
