package com.hs.meetme.coupleinfo.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoupleInfoVO {
	private int coupleId;
	private String coupleStatus; //연결중 y, 끊김 n 
	private int userRequest; //커플신청유저
	private int userReceived; //신청받은유저
	private Date coupleDate; //사귀기 시작한 날짜
	private int imageId;	//커플대문
	private int subTerm;	//구독기간(1,3,6,0)
	private Date startDate; //결제날(시작날)
	private Date imgUrl; //이미지 url
}
