package com.hs.meetme.useraccess.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.useraccess.domain.AccountVO;

@Mapper
public interface AccountMapper {
	public AccountVO findByEmail(String email);
	public int emailCheck(String email);
	public void signUp(AccountVO vo);
}
