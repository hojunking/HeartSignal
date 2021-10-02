package com.hs.meetme.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.service.MypageService;

@RestController
@RequestMapping("/mypage/*")
public class MypageRestController {

	@Autowired MypageService mypageService;
	@Autowired private PasswordEncoder encoder;
	
	//수정하기 (주소)
	@PutMapping("/addressUpdate")
	public MyPageUserInfoVO addressUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO) {
		
		mypageService.updateAddress(myPageUserInfoVO);
		
		return myPageUserInfoVO;
	}
	//수정하기 (비밀번호)
	@PutMapping("/passwordUpdate")
	public boolean passwordUpdate(@RequestBody MyPageUserInfoVO vo) {
		String newPassword = vo.getPassword();//새로 입력한 비밀번호
		
		// 암호화 된 비밀번호
		String encodedPw = encoder.encode(newPassword);

		vo.setPassword(encodedPw);	
	
		int r = mypageService.userUpdatePassword(vo);
		
		return r == 1 ? true : false;
	}
}
