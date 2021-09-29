package com.hs.meetme.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.post.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{
	
	@Autowired PostMapper pMapper;
	
	@Override
	public List<PostVO> getCMList() {
		// TODO Auto-generated method stub
		return pMapper.getCMList();
	}

	@Override
	public List<PostVO> getRCList() {
		// TODO Auto-generated method stub
		return pMapper.getRCList();
	}

	@Override
	public PostVO getPost(long idx) {
		// TODO Auto-generated method stub
		return pMapper.getPost(idx);
	}

	@Override
	public int postInsert() {
		// TODO Auto-generated method stub
		return pMapper.postInsert();
	}

	@Override
	public int postUpdate(long idx) {
		// TODO Auto-generated method stub
		return pMapper.postUpdate(idx);
	}

	@Override
	public int postDelete(long idx) {
		// TODO Auto-generated method stub
		return pMapper.postDelete(idx);
	}

}
