package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
public class PostCommentVO {
  
	private String commentId;
	private String comContent;
	private Date comCreated;
	private Date comUpdated;
	private String comDistinct;
	
	private String userId;
	private String postId;
	private String logId;
}
