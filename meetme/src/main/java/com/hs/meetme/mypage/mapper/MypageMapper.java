package com.hs.meetme.mypage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.mypage.domain.Criteria;
import com.hs.meetme.mypage.domain.PostVO;
import com.hs.meetme.mypage.domain.UserInfoVO2;

@Mapper
public interface MypageMapper {
  
	//내 게시글 전체 갯수
	public int getPostCount(Criteria cri);
	//내 게시글 조회
	public List<PostVO> getPostList(Criteria cri);
	//내 게시글 페이징 위한 갯수
	public int getTotalCount(Criteria cri);
	
	
	//회원정보 불러오기
	public UserInfoVO2 getMyinfo(UserInfoVO2 userInfoVO);
}
