package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
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
	private String address1;
	private String address2;
	private String address3;
	private String coupleStatus;
	private String imgId;
	
	private String imgUrl;
	private String imgName;
	private String imgSize;
	private Date imgDate;
	private String imgUsage;
	
}
