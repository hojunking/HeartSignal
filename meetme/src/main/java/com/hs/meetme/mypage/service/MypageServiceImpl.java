package com.hs.meetme.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PostCommentVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.UserInfoVO2;
import com.hs.meetme.mypage.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
    
	@Autowired MypageMapper mypageMapper;
	
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


	@Override
	public int getCommentCount(long userId) {
		return mypageMapper.getCommentCount(userId);
	}

	@Override
	public List<PostCommentVO> getCommentList(Criteria cri, long userId) {
		return mypageMapper.getCommentList(cri, userId);
	}

	@Override
	public int getTotalCommentCount(Criteria cri, long userId) {
		return mypageMapper.getTotalCommentCount(cri, userId);
	}

	@Override
	public UserInfoVO2 getMyinfo(UserInfoVO2 userInfoVO) {
		return mypageMapper.getMyinfo(userInfoVO);
	}
}
