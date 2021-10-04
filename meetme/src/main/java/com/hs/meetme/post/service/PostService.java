package com.hs.meetme.post.service;

import java.util.List;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.PostVO;

public interface PostService {

	List<PostVO> getCMList();//커뮤니티글 전체조회
	List<PostVO> getRCList();//추천해요글 전체조회
	
	public PostVO getPost(long idx);//글 한 건 조회

	public int CMInsert(PostVO vo); //커뮤니티 인서트
	public int RCInsert(PostVO vo); //추천 인서트
	public int postUpdate(PostVO vo);
	public int postDelete(PostVO vo);
	public int postLike(long userId); // 글 좋아요
	
	public long countHit(long postId); //조회수 up
	public List<CommentVO> commentCM(long postId);//커뮤니티 댓글 가져오기
	public int insertCMComment(CommentVO vo); // 커뮤니티댓글 입력
	
	
	public int commentDelete(long commentId); // 댓글 삭제
	public int commentUpdate(CommentVO vo);//댓글 수정

}
