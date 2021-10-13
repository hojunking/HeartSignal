package com.hs.meetme.useraccess.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hs.meetme.useraccess.domain.AccountVO;

public interface AccountService extends UserDetailsService{
	public void signUp(AccountVO vo);
	public int emailCheck(String email);
	public int nicknameCheck(String nickname);
	public int phoneNumCheck(String phoneNum);
	public int updatePwd(AccountVO vo);
}
