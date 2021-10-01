package com.hs.meetme.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.CommentVO;
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
	public int CMInsert(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.CMInsert(vo);
	}

	@Override
	public int RCInsert(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.RCInsert(vo);
	}

	@Override
	public int postUpdate(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.postUpdate(vo);
	}

	@Override
	public int postDelete(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.postDelete(vo);
	}

	@Override
	public long countHit(long postId) {
		// TODO Auto-generated method stub
		return pMapper.countHit(postId);
	}

	@Override
	public List<CommentVO> commentCM(long postId) {
		// TODO Auto-generated method stub
		return pMapper.commentCM(postId);
	}

	@Override
	public int commentDelete(long commentId) {
		return pMapper.commentDelete(commentId);
	}

	@Override
	public int commentInsert(CommentVO vo) {
		return pMapper.commentInsert(vo);
	}


	
}
