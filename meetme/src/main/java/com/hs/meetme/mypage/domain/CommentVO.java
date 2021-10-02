package com.hs.meetme.mypage.domain;

import java.util.Date;

import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.Data;

@Data
public class CommentVO extends AccountVO{
  
	private String commentId;
	private String comContent;
	private Date comCreated;
	private Date comUpdated;
	
	private String userId;
	private String postId;
	private String logId;
}
