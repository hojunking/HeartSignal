package com.hs.meetme.useraccess.domain;

import java.util.Date;

import lombok.Data;

@Data
public class UserinfoVO {
	private String userId;
	private String userName;
	private String email;
	private String nickname;
	private String password;
	private String phoneNum;
	private Date created;
	private Date updated;
	private Date birth_date;
	private String address;
	private String coupleStatus;
	private String imgId;
}
