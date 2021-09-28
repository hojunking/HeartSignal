package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PostCommentVO {
  
	private long commentId;
	private String comContent;
	private Date comCreated;
	private Date comUpdated;
	
	private long userId;
	private long postId;
	private long logId;
}
