package com.hs.meetme.mypage.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PostVO {
  
  private long postId;         //게시글 번호
  private String postTitle;    //게시글 제목
  @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
  private Date postUpdated;  //게시글 날짜
  private long userId;       //회원 아이디
  private long hit;            //게시글 조회수
  private String postDistinct; //게시글 구분
  
  private long postCount;      //게시글 갯수
  
}
