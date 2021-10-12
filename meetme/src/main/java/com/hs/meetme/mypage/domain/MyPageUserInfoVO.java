package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyPageUserInfoVO{

	private String userId;
	private String name;
	private String email;
	private String nickName;
	private String password;
	private String phoneNum;
	private Date created;
	private Date updated;
	private String birthYear;
	private String birthDay;
	private String addrzonecode;
	private String addrfull;
	private String addrdetail;
	private String coupleStatus;
	private String imgId;
	
	private String imgUrl;
	private String imgName;
	private String imgSize;
	private Date imgDate;
	private String imgUsage;
		
}
