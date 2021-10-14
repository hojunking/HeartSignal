package com.hs.meetme.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.mypage.domain.CommentVO;
import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.MyPageCourseVO;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.post.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{
   
   @Autowired PostMapper pMapper;
   


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
   public int insertCMComment(CommentVO vo) {
      // TODO Auto-generated method stub
      return pMapper.insertCMComment(vo);
   }

   @Override
   public int commentUpdate(CommentVO vo) {
      // TODO Auto-generated method stub
      return pMapper.commentUpdate(vo);
   }

   @Override
   public int postLike(long postId,long userId) {
      // TODO Auto-generated method stub
      return pMapper.postLike(postId, userId);
   }

   @Override
   public int getCmNum() {
      // TODO Auto-generated method stub
      return pMapper.getCmNum();
   }

   @Override
   public int getTotalCmNum(Criteria cri) {
      // TODO Auto-generated method stub
      return pMapper.getTotalCmNum(cri);
   }

	@Override
	public List<PostVO> getCMList(Criteria cri) {
		// TODO Auto-generated method stub
		return pMapper.getCMList(cri);
	}

	@Override
	public List<MyPageCourseVO> getCourseList(MyPageCourseVO vo) {
		// TODO Auto-generated method stub
		return pMapper.getCourseList(vo);
	}

	@Override
	public List<MyPageCourseVO> getCourse(String courseId) {
		// TODO Auto-generated method stub
		return pMapper.getCourse(courseId);
	}

	@Override
	public int scrapCourse(long courseId, long userId) {
		// TODO Auto-generated method stub
		return pMapper.scrapCourse(courseId, userId);
	}

	@Override
	public int getCourseScrap(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.getCourseScrap(vo);
	}

	@Override
	public int getPostLike(PostVO vo) {
		// TODO Auto-generated method stub
		return pMapper.getPostLike(vo);
	}

	@Override
	public int postLikeCancle(long postId,long userId) {
		// TODO Auto-generated method stub
		return pMapper.postLikeCancle( postId, userId);
	}

	@Override
	public int scrapCancel(long courseId, long userId) {
		// TODO Auto-generated method stub
		return pMapper.scrapCancel(courseId,userId);
	}

	@Override
	public int countCM(long postId) {
		// TODO Auto-generated method stub
		return pMapper.countCM(postId);
	}                                                                              

	

}