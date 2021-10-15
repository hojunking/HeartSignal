package com.hs.meetme.notice.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeVO {
	private String noticeId;
	private String userSent;
	private String userReceived;
	private String noticeContent;
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	private Date noticeDate;
	private String noticeConfirmed;
	
	private String postId;
	
	private String userId;
	private String name;
	private String imgUrl; //요청자 이미지 url
}
