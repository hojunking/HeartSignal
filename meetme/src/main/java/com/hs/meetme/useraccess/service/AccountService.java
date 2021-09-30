package com.hs.meetme.useraccess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hs.meetme.useraccess.domain.AccountVO;
import com.hs.meetme.useraccess.mapper.AccountMapper;

@Service
public class AccountService implements UserDetailsService{
	@Autowired
	AccountMapper accountMapper;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		AccountVO account = accountMapper.findByEmail(email);
		
		if( account == null ) {
			throw new UsernameNotFoundException(email);
		}
		
		System.out.println(account.toString());
		return account;
	}

}
