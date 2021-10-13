package com.hs.meetme.useraccess.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@SuppressWarnings("serial")
@Data
public class AccountVO implements UserDetails{

	private String userId;
	private String name;
	private String email;
	private String nickname;
	private String password;
	private String phoneNum;
	private Date created;
	private Date updated;
	private String addrzonecode;
	private String coupleStatus;
	private String imgId;
	
	private String role;
	private String addrfull;
	private String addrdetail;
	private String birthYear;
	private String birthDay;
	
	private String coupleId;
	private String userRequest;
	private String userReceived;
	private String subTerm;
	private String startDate;

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
		authList.add(new SimpleGrantedAuthority(role));
		return authList;
	}

}
