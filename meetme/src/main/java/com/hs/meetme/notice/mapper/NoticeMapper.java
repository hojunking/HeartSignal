package com.hs.meetme.notice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.notice.domain.NoticeVO;
@Mapper
public interface NoticeMapper {
	//notice
		public List<NoticeVO> getNoticeList(NoticeVO vo); //나의 notice정보 들고오기
		public int deleteNotice(NoticeVO noticeVO);       //나의 notice정보 삭제하기
}
