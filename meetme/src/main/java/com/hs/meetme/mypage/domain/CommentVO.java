package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
  
	private String commentId;
	private String comContent;
	private Date comCreated;
	private Date comUpdated;
	
	private String userId;
	private String postId;
	private String logId;
}
