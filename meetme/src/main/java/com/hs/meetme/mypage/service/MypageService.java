package com.hs.meetme.mypage.service;

import java.util.List;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;

public interface MypageService {
	
	//내 게시글 전체 갯수
	public int getPostCount(long userId);
	//내 게시글 조회
	public List<PostVO> getPostList(Criteria cri, long userId);
	//내 게시글 페이징 위한 갯수
	public int getTotalCount(Criteria cri, long userId);
	
	//내 게시글 전체 갯수
	public int getCommentCount(long userId);
	//내 게시글 조회
	public List<CommentVO> getCommentList(Criteria cri, long userId);
	//내 게시글 페이징 위한 갯수
	public int getTotalCommentCount(Criteria cri, long userId);
	
	//회원정보 불러오기
	public MyPageUserInfoVO getMyinfo(MyPageUserInfoVO myPageuserInfoVO);
	//회원정보 수정
	public int setDateUpdated(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 수정(주소)
	public int updateAddress(MyPageUserInfoVO myPageUserInfoVO);
}
