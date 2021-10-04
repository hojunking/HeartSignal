package com.hs.meetme.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.MyPageUserInfoVO;
import com.hs.meetme.mypage.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
    
	@Autowired MypageMapper mypageMapper;
	
	//마이페이지 나의 게시글 관련
	@Override
	public int getPostCount(long userId) {
		return mypageMapper.getPostCount(userId);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri, long userId) {
		return mypageMapper.getPostList(cri, userId);
	}

	@Override
	public int getTotalCount(Criteria cri, long userId) {
		return mypageMapper.getTotalPostCount(cri, userId);
	}

	//마이페이지 나의 댓글 관련
	@Override
	public int getCommentCount(long userId) {
		return mypageMapper.getCommentCount(userId);
	}

	@Override
	public List<CommentVO> getCommentList(Criteria cri, long userId) {
		return mypageMapper.getCommentList(cri, userId);
	}

	@Override
	public int getTotalCommentCount(Criteria cri, long userId) {
		return mypageMapper.getTotalCommentCount(cri, userId);
	}
    
	//마이페이지 userinfo 관련
	@Override
	public MyPageUserInfoVO getMyinfo(MyPageUserInfoVO MyPageuserInfoVO) {
		return mypageMapper.getMyinfo(MyPageuserInfoVO);
	}

	@Override
	public int updateAddress(MyPageUserInfoVO myPageUserInfoVO) {
		return mypageMapper.updateAddress(myPageUserInfoVO);
	}

	@Override
	public MyPageUserInfoVO userSelectPassword(MyPageUserInfoVO myPageUserInfoVO) {
		return mypageMapper.userSelectPassword(myPageUserInfoVO);
	}

	@Override
	public int userUpdatePassword(MyPageUserInfoVO myPageUserInfoVO) {
		return mypageMapper.userUpdatePassword(myPageUserInfoVO);
	}

	@Override
	public MyPageUserInfoVO userSelectNickName(MyPageUserInfoVO myPageUserInfoVO) {
		return mypageMapper.userSelectNickName(myPageUserInfoVO);
	}

	@Override
	public int userUpdateNickName(MyPageUserInfoVO myPageUserInfoVO) {
		return mypageMapper.userUpdateNickName(myPageUserInfoVO);
	}
}
