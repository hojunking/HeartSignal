package com.hs.meetme.useraccess.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hs.meetme.useraccess.domain.AccountVO;

public interface AccountService extends UserDetailsService{
	public void signUp(AccountVO vo);
}
