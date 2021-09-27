package com.hs.meetme.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.mapper.MypageMapper;

@Service
public class MypageServiceImpl implements MypageService {
    
	@Autowired MypageMapper mypageMapper;
	
	@Override
	public int getPostCount(Criteria cri) {
		return mypageMapper.getPostCount(cri);
	}

	@Override
	public List<PostVO> getPostList(Criteria cri) {
		return mypageMapper.getPostList(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return mypageMapper.getTotalCount(cri);
	}

}
