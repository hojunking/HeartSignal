package com.hs.meetme.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;

@RestController
public class NoticeRestController {
	@Autowired NoticeService noticeService;
	@Autowired MypageService userService;
	
	@PostMapping("/request") //커플메인에서 커플요청 시 notice INSERT
	public String userRequest(NoticeVO vo) {
		MyPageUserInfoVO myVo =new MyPageUserInfoVO();
		
		myVo.setUserId(vo.getUserSent());
		myVo= userService.getMyinfo(myVo);
		
		vo.setNoticeContent(myVo.getNickName()+"님이 커플 신청을 하셨습니다./n"
				+ "수락하시겠습니까?");
		
		noticeService.insertNotice(vo);
		
		return "요청을 보냈습니다.";
	}
	
	
	@GetMapping("/getRequest") //커플요청정보만 알려주기
	public NoticeVO getRequest(NoticeVO vo) {
		return noticeService.coupleRequest(vo);
	}
}
