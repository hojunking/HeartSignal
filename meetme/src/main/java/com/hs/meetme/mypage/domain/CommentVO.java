package com.hs.meetme.mypage.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CommentVO {
  
	private String commentId;
	private String comContent;
	private Date comCreated;
	private Date comUpdated;
	private String comDistinct;
	
	private String userId;
	private String postId;
	private String logId;
	
	private String nickname;
	private String imgUrl;
	
	private String postUserId; //게시글 작성자의 아이디
}
