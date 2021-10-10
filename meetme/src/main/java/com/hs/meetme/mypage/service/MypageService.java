package com.hs.meetme.mypage.service;

import java.util.List;


import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.UserTagsVO;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;

public interface MypageService {
	
	//내 취향 조회하기
	public List<UserTagsVO> getUserTags(UserTagsVO userTagsVO);
	
	//내 코스 조회
	public List<MyPageCourseVO> getCourseList(Criteria cri, String userId);
	//내 코스 페이징 위한 갯수
	public int getTotalCourseCount(Criteria cri, String userId);
	//내 코스 상세 리스트 조회
	public List<MyPageCourseVO> getCourseDetailList(MyPageUserInfoVO myPageUserInfoVO);
	//코스 스크랩 한거 삭제
	public int deleteCourseLike(MyPageCourseVO myPageCourseVO);
	//내가 만든 코스 삭제
	public int deleteCourse(MyPageCourseVO myPageCourseVO);
	public int deleteCourseLike2(MyPageCourseVO myPageCourseVO);
	
	
	//내 게시글 전체 갯수
	public int getPostCount(String userId);
	//내 게시글 조회
	public List<PostVO> getPostList(Criteria cri, String userId);
	//내 게시글 페이징 위한 갯수
	public int getTotalPostCount(Criteria cri, String userId);
	
	//내 게시글 전체 갯수
	public int getCommentCount(String userId);
	//내 게시글 조회
	public List<CommentVO> getCommentList(Criteria cri, String userId);
	//내 게시글 페이징 위한 갯수
	public int getTotalCommentCount(Criteria cri, String userId);
	
	//회원정보 불러오기
	public MyPageUserInfoVO getMyinfo(MyPageUserInfoVO myPageuserInfoVO);
	//회원정보 수정(주소)
	public int updateAddress(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 비밀번호 불러오기
	public MyPageUserInfoVO userSelectPassword(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 수정(비밀번호)
	public int userUpdatePassword(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 닉네임 불러오기
	public MyPageUserInfoVO userSelectNickName(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 수정(닉네임)
	public int userUpdateNickName(MyPageUserInfoVO myPageUserInfoVO);
	
	}
