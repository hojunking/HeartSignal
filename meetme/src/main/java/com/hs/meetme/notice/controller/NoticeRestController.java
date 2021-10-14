package com.hs.meetme.notice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@GetMapping("/getRequest/{userId}") //커플요청정보만 알려주기 리스트로 뿌려야할 가능성
	public List<NoticeVO> getRequest(@PathVariable String userId, NoticeVO vo) {
		System.out.println("여기까지 왔으면 보여줘 "+ userId);
		vo.setUserReceived(userId);				//receiver의 아이디
		List<NoticeVO> list = noticeService.coupleRequest(vo);	//신청정보들고오기
		System.out.println(list); 
		return list;
	}
}
