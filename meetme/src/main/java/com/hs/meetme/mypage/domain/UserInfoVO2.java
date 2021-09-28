package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserInfoVO2 {

	private long userId;
	private String userName;
	private String email;
	private String nickName;
	private String password;
	private String phoneNum;
	private Date created;
	private Date updated;
	private Date birthDate;
	private String address;
	private String coupleStatus;
	private long imgId;
	
	private String imgUrl;
	private String imgName;
	private String imgSize;
	private Date imgDate;
	private String imgUsage;
	
}
