package com.hs.meetme.mypage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.useraccess.domain.AccountVO;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired MypageService mypageService;
	
	// 나의 코스 리스트 보기
	@GetMapping("/myinfo_my_course_list")
	public String myinfo_my_course_list(Model model,
                                        @ModelAttribute("cri") Criteria cri,
                                        MyPageUserInfoVO vo,
                                        HttpServletRequest request) {
		//세션 쓰는법
				HttpSession session = request.getSession();
				AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
				String userId = accountVO.getUserId();
				
				cri.setUserId(userId);
				vo.setUserId(userId);

				int total = mypageService.getTotalCourseCount(cri, userId);
				model.addAttribute("list", mypageService.getCourseList(cri, userId));
				model.addAttribute("pageMaker", new PageVO(cri, total));
				
				model.addAttribute("detail",mypageService.getCourseDetailList(vo));
		
		return "mypage/myinfo_my_course_list";
	}
	
	// 내 글 리스트 보기
	@GetMapping("/myinfo_my_write_list")
	public String myinfo_my_write_list(Model model, 
			                           @ModelAttribute("cri") Criteria cri,
			                           HttpServletRequest request) {
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		int total = mypageService.getTotalPostCount(cri, userId);
		
		model.addAttribute("count",mypageService.getPostCount(userId));
		
		model.addAttribute("list", mypageService.getPostList(cri, userId));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		
		return "mypage/myinfo_my_write_list";
	}
	
	// 내 댓글 리스트 보기
	@GetMapping("/myinfo_my_comment_list")
	public String myinfo_my_comment_list(Model model, 
			                             @ModelAttribute("cri") Criteria cri,
			                             HttpServletRequest request) {
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		int total = mypageService.getTotalCommentCount(cri, userId);
		
		model.addAttribute("count",mypageService.getCommentCount(userId));
		
		model.addAttribute("list", mypageService.getCommentList(cri, userId));
		model.addAttribute("pageMaker", new PageVO(cri, total));
		
		return "mypage/myinfo_my_comment_list";
	}
	
	// 유저 인포 보기
	@GetMapping("/myinfo")
	public String myinfo(Model model, MyPageUserInfoVO myPageUserInfoVO, HttpServletRequest request) {
		
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		myPageUserInfoVO.setUserId(userId);
		model.addAttribute("userInfo", mypageService.getMyinfo(myPageUserInfoVO));
		
		return "mypage/myinfo";
	}
	
}
