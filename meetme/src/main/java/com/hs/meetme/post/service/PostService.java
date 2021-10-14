package com.hs.meetme.post.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.PostVO;

public interface PostService {

   List<PostVO> getCMList(Criteria cri);//커뮤니티글 전체조회
   public int getCmNum();//커뮤니티 글 전체 개수
   public int getTotalCmNum(Criteria cri);// 커뮤니티 글 페이징 위한 개수
   
   List<PostVO> getRCList();//추천해요글 전체조회
   
   public PostVO getPost(long idx);//글 한 건 조회

   public int CMInsert(PostVO vo); //커뮤니티 인서트
   public int RCInsert(PostVO vo); //추천 인서트
   public int postUpdate(PostVO vo);
   public int postDelete(PostVO vo);
   public int postLike(long postId,long userId); // 글 좋아요
   public int scrapCourse(long courseId, long userId); //코스 스크랩하기
   
   public long countHit(long postId); //조회수 up
   public List<CommentVO> commentCM(long postId);//커뮤니티 댓글 가져오기
   public int insertCMComment(CommentVO vo); // 커뮤니티댓글 입력
   
   
   public int commentDelete(long commentId); // 댓글 삭제
   public int commentUpdate(CommentVO vo);//댓글 수정
   
   public List<MyPageCourseVO> getCourseList(MyPageCourseVO vo);// 내 코스 조회
   public List<MyPageCourseVO> getCourse(String courseId);// 코스 하나 들고오기 
   public int getCourseScrap(PostVO vo);//코스 스크랩한거 들고오기
   public int getPostLike(PostVO vo);//게시글 좋아요 들고오기
   public int postLikeCancle(long postId,long userId);//좋아요 취소하기
   public int scrapCancel(long courseId, long userId);//코스 스크랩 취소
   public int countCM(long postId);//댓글 갯수 세기
}