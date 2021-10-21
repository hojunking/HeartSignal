package com.hs.meetme.coupleinfo.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hs.meetme.image.domain.ImageVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoupleInfoVO extends ImageVO{
	private int coupleId;
	private int userRequest; //커플신청유저
	private int userReceived; //신청받은유저
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date coupleDate; //사귀기 시작한 날짜
	private int imageId;	//커플대문
	private int subTerm;	//구독기간(1,3,6,0)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date startDate; //결제날(시작날)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date endDate; //만료일
	private String coupleImg; //커플이미지 url
	
	//공동으로 사용함
	private String coupleStatus; //솔로=n ,커플연결중=y, 구독기간만료=e, 커플매칭대기중 w 
	// couple 테이블의 coupleStatus 활성화 y,비활성화 n
	//유저 정보에 필요한 vo
	private int userId;
	private String name;
	private String nickName;
	private String birthDay;  
	private String userImg; //유저이미지 url
	
	private String imgUrl; //커플이미지 url
	private String email;
	private String message; //메시지보내기
}



