package com.hs.meetme.mypage.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.domain.PageVO;
import com.hs.meetme.mypage.domain.UserTagsVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.mapper.NoticeMapper;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.useraccess.domain.AccountVO;

@Controller
@RequestMapping("/mypage/*")
public class MypageController {

	@Autowired MypageService mypageService;
	@Autowired NoticeService noticeService;
	
	//나의 페이지 메인 -> 나의 취향 보기 / 알림내역 보기
	@GetMapping("/main")
	public String main(Model model, MyPageUserInfoVO myPageUserInfoVO, UserTagsVO userTagVO, NoticeVO noticeVO, HttpServletRequest request) {
		
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		myPageUserInfoVO.setUserId(userId);
		userTagVO.setUserId(userId);
		noticeVO.setUserReceived(userId);
		
		String myAddress=null;
		if(mypageService.getMyinfo(myPageUserInfoVO).getAddrfull()!=null) {	
		myAddress = mypageService.getMyinfo(myPageUserInfoVO).getAddrfull();
		String[ ] array = myAddress.split(" ");
		myAddress = array[0]+" "+array[1];
		}

		model.addAttribute("userInfo", mypageService.getMyinfo(myPageUserInfoVO));
		model.addAttribute("userTag", mypageService.getUserTags(userTagVO));
		
		model.addAttribute("userNotice", noticeService.getNoticeList(noticeVO));
		
		model.addAttribute("myAddress",myAddress);
		
		return "mypage/main";
	}
	
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
	
	//결제 내역 보기
	@GetMapping("/myinfo_my_payment_list")
	public String myinfo_my_payment_list(Model model,MyPageUserInfoVO myPageUserInfoVO, HttpServletRequest request) {
		//세션 쓰는법
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		myPageUserInfoVO.setUserId(userId);
		
		String myAddress=null;
		if(mypageService.getMyinfo(myPageUserInfoVO).getAddrfull()!=null) {	
		myAddress = mypageService.getMyinfo(myPageUserInfoVO).getAddrfull();
		String[ ] array = myAddress.split(" ");
		myAddress = array[0]+" "+array[1];
		}

		model.addAttribute("userInfo", mypageService.getMyinfo(myPageUserInfoVO));
		
		model.addAttribute("myAddress",myAddress);
		return "mypage/myinfo_my_payment_list";
	}
	
	//메인 유저정보 
	@GetMapping("/mainMyinfo")
	public String mainMyinfo(Model model, MyPageUserInfoVO myPageUserInfoVO, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO)session.getAttribute("userSession");
		String userId = accountVO.getUserId();
		
		myPageUserInfoVO.setUserId(userId);
		model.addAttribute("userInfo", mypageService.getMyinfo(myPageUserInfoVO));
		return "mypage/myinfo_main";
	}
	
	@GetMapping("/getNoticeList")
	@ResponseBody
	public List<NoticeVO> getMynotice(NoticeVO vo){
		System.out.println("여기 왔니 ?"+vo.getUserId());
		System.out.println(noticeService.getNoticeList(vo));
		return noticeService.getNoticeList(vo);
	}
}
