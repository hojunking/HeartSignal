package com.hs.meetme.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.service.MypageService;

@RestController
@RequestMapping("/mypage/*")
public class MypageRestController {

	@Autowired MypageService mypageService;
	@Autowired private PasswordEncoder encoder;
	
	@DeleteMapping("/deleteCourse")
	public boolean deleteCourse(@RequestBody MyPageCourseVO myPageCourseVO) {
		
		int r =mypageService.deleteCourseLike(myPageCourseVO);
		
		return r == 1 ? true : false;
	}
	
	//현재 비밀번호 확인
	@PostMapping("/passwordRead")
	public boolean passwordRead(@RequestBody MyPageUserInfoVO vo) {
		String currentPassword = vo.getPassword();
		String dbPassword = mypageService.userSelectPassword(vo).getPassword();
		
		return encoder.matches(currentPassword, dbPassword);
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
	
	//닉네임 중복 확인
	@PostMapping("/nickNameRead")
	public boolean nickNameRead(@RequestBody MyPageUserInfoVO vo) {
		if( mypageService.userSelectNickName(vo) == null) {
		  	return true;
		} else {
			return false;
		}
	}
	//수정하기 (닉네임)
	@PutMapping("/nickNameUpdate")
	public MyPageUserInfoVO nickNameUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO) {
		
		mypageService.userUpdateNickName(myPageUserInfoVO);
		
		return myPageUserInfoVO;
	}
	
	//수정하기 (주소)
	@PutMapping("/addressUpdate")
	public MyPageUserInfoVO addressUpdate(@RequestBody MyPageUserInfoVO myPageUserInfoVO) {
		
		mypageService.updateAddress(myPageUserInfoVO);
		
		return myPageUserInfoVO;
	}
	
	//마이페이지 내정보 뿌링클
	
	
}
