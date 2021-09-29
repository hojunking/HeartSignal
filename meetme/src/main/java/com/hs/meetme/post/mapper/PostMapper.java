package com.hs.meetme.post.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.mypage.domain.PostVO;

@Mapper
public interface PostMapper {
	List<PostVO> getCMList();//커뮤니티글 전체조회
	List<PostVO> getRCList();//추천해요글 전체조회
	
	public PostVO getPost(long idx);//글 한 건 조회

	public int postInsert();
	public int postUpdate(long idx);
	public int postDelete(long idx);
	
}
