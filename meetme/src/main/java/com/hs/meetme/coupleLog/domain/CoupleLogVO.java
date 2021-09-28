package com.hs.meetme.coupleLog.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class CoupleLogVO {
	private int logId;
	private Date logCreated;
	private Date logUpdated;
	private String logTitle;
	private String logContent;
	private String logState;
	private int coupleId; //커플 id 가져와야하남?
	private int logLoc; //데이트 한 장소
	private Date logDate; //데이트 한 날짜
}
