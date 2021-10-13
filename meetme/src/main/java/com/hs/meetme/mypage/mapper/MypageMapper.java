package com.hs.meetme.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.UserTagsVO;
import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.payment.domain.PaymentVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;

@Mapper
public interface MypageMapper {
    
	//결제 내역 가져오기
	public List<PaymentVO> readPayment(PaymentVO paymentVO);
	
	//태그 다 가져오기
	public List<UserTagsVO> getTags(UserTagsVO userTagsVO);
	//나의 취향 태그 가져 오기
	public List<UserTagsVO> getUserTags(UserTagsVO userTagsVO);
	//나의 취향 태그 삭제
	public int deleteUserTags(UserTagsVO userTagsVO);
	//나의 취향 태그 인서트
	public int insertUserTags(UserTagsVO userTagsVO);
	
	//내 코스 리스트 조회
	public List<MyPageCourseVO> getCourseList(@Param("cri") Criteria cri, @Param("userId") String userId);
	//내 코스 페이징 위한 갯수
	public int getTotalCourseCount(@Param("cri") Criteria cri, @Param("userId") String userId);
	//내 코스 상세 리스트 조회
	public List<MyPageCourseVO> getCourseDetailList(MyPageUserInfoVO myPageUserInfoVO);
	//코스 스크랩 한거 삭제
	public int deleteCourseLike(MyPageCourseVO myPageCourseVO);
	//내가 만든 코스 삭제
	public int deleteCourse(MyPageCourseVO myPageCourseVO);
	public int deleteCourseLike2(MyPageCourseVO myPageCourseVO);
	
	//내 게시글 전체 갯수
	public int getPostCount(@Param("userId") String userId);
	//내 게시글 조회
	public List<PostVO> getPostList(@Param("cri") Criteria cri, @Param("userId") String userId);
	//내 게시글 페이징 위한 갯수
	public int getTotalPostCount(@Param("cri") Criteria cri, @Param("userId") String userId);
	
	//내 댓글 전체 갯수
	public int getCommentCount(@Param("userId") String userId);
	//내 댓글 조회
	public List<CommentVO> getCommentList(@Param("cri") Criteria cri, @Param("userId") String userId);
	//내 댓글 페이징 위한 갯수
	public int getTotalCommentCount(@Param("cri") Criteria cri, @Param("userId") String userId);
	
	//회원정보 불러오기
	public MyPageUserInfoVO getMyinfo(MyPageUserInfoVO myPageUserInfoVO);
	//회원정보 수정(프로필사진)
	public int userUpdateImage(MyPageUserInfoVO myPageUserInfoVO);
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
