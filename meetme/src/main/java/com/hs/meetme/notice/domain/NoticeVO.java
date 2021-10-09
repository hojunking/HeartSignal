package com.hs.meetme.notice.domain;

import java.util.Date;


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
	private Date noticeDate;
	private String noticeConfirmed;
	
	private String userId;
	
}
