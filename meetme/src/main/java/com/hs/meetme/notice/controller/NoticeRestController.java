package com.hs.meetme.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.service.CoupleInfoService;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.service.MypageService;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.service.NoticeService;
import com.hs.meetme.useraccess.domain.AccountVO;

@RestController
public class NoticeRestController {
	@Autowired NoticeService noticeService;
	@Autowired MypageService userService;
	@Autowired CoupleInfoService coupleService;
	
	@PostMapping("/request") //커플메인에서 커플요청 시 notice INSERT
	public String userRequest(NoticeVO vo, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		AccountVO accountVO = (AccountVO) session.getAttribute("userSession");
		
		MyPageUserInfoVO myVo =new MyPageUserInfoVO();
		myVo.setUserId(vo.getUserSent());
		myVo= userService.getMyinfo(myVo);
		
		vo.setNoticeContent(myVo.getNickName()+"님이 커플 신청을 하셨습니다.\n"
				+ "수락하시겠습니까?");
		
		noticeService.insertNotice(vo);
		
		int userId= Integer.valueOf(vo.getUserSent()); //유저상태를 s로 변환하는 작업
		CoupleInfoVO cvo=new CoupleInfoVO();
		cvo.setUserId(userId);
		cvo.setCoupleStatus("s");
		coupleService.userCoupleStatusUpdate(cvo);
		accountVO.setCoupleStatus("s"); //세션변경사항 저장
		
		return "요청을 보냈습니다.";
	}
	
	
	@GetMapping("/getRequest/{userId}") //커플요청정보만 알려주기 리스트로 뿌려야할 가능성
	public List<NoticeVO> getRequest(@PathVariable String userId, NoticeVO vo) {
		
		vo.setUserReceived(userId);				//receiver의 아이디
		List<NoticeVO> list = noticeService.coupleRequest(vo);	//신청정보들고오기
		return list;
	}
	
	@PutMapping("/updateConfirmed/{id}") //클릭 시 confirm으로 변경되는 메소드
	public void updateConfirmed(NoticeVO vo,@PathVariable String id) {
		vo.setNoticeId(id);
		noticeService.confirmUpdate(vo);
	}
	@GetMapping("/countNotice/{id}") //클릭 시 countNotice
	public int getCountNotice(@PathVariable String id,NoticeVO vo) {
		vo.setUserReceived(id);
		vo= noticeService.countNotice(vo);
		
		return vo.getCount();
	}
}
