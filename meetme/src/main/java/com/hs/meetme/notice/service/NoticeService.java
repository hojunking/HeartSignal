package com.hs.meetme.notice.service;

import java.util.List;

import com.hs.meetme.notice.domain.NoticeVO;

public interface NoticeService {
	//notice
			public List<NoticeVO> getNoticeList(NoticeVO vo); //나의 notice정보 들고오기
			public List<NoticeVO> coupleRequest(NoticeVO vo); 		//커플요청 시 정보
			public int deleteNotice(NoticeVO noticeVO);       //나의 notice정보 삭제하기
			public int insertNotice(NoticeVO vo); 			//알림 보내기(커플신청)
			public int confirmUpdate(NoticeVO vo); 			//알림 읽었을때 상태 변화
			public NoticeVO countNotice(NoticeVO vo); 		//알림카운트
}
