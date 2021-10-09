package com.hs.meetme.mypage.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hs.meetme.useraccess.domain.AccountVO;

import lombok.Data;

@Data
public class PostVO extends AccountVO{
  
	private String postId; //게시물 번호 시퀀스
	private String postTitle; 
	private String postContent;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date postCreated;
	private Date postUpdated;
	private String userId; //작성자 id
	private String courseId;//코스 id
	private String hit;
	private String postDistinct;//커뮤니티 c, 추천 r
	
	//다른 테이블 꺼
	private String postLikeCount; //게시글 추천수
  
	private String postCount;      //게시글 갯수
  
}