package com.hs.meetme.coupleCourse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coupleCourse.domain.CoupleCourseVO;
import com.hs.meetme.mypage.domain.MyPageCourseVO;

@Mapper
public interface CoupleCourseMapper {
	public List<CoupleCourseVO> getList(String coupleId);

	public List<CoupleCourseVO> getCourse(String coupleId);
	// 내가 만든 코스 삭제
	public int deleteCourse(CoupleCourseVO vo);

}
