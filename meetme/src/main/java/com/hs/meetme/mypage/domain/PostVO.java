package com.hs.meetme.mypage.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostVO {
  
  private String postId;         //게시글 번호
  private String postTitle;    //게시글 제목
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date postCreated;    //게시글 생성 날짜
  private Date postUpdated;    //게시글 수정 날짜
  private String userId;         //회원 아이디
  private String courseId;
  private String hit;            //게시글 조회수
  private String postDistinct; //게시글 구분
  
  //다른 테이블 꺼
  private String postLikeCount;       //게시글 추천수
  
  private String postCount;      //게시글 갯수
  
}