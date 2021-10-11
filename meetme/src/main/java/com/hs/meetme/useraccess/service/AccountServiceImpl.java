package com.hs.meetme.useraccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hs.meetme.useraccess.domain.AccountVO;
import com.hs.meetme.useraccess.mapper.AccountMapper;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountMapper accountMapper;
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AccountVO account = accountMapper.findByEmail(email);
//		if( account == null ) {
//			throw new UsernameNotFoundException(email);
//		}
		return account;
	}

	@Override
	public void signUp(AccountVO vo) {
		vo.setPassword(encoder.encode(vo.getPassword()));
		accountMapper.signUp(vo);
	}

	@Override
	public int emailCheck(String email) {
		return accountMapper.emailCheck(email);
	}

	@Override
	public int nicknameCheck(String nickname) {
		return accountMapper.nicknameCheck(nickname);
	}

}
