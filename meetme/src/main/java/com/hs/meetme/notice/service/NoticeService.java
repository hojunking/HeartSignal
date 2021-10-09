package com.hs.meetme.notice.service;

import java.util.List;

import com.hs.meetme.notice.domain.NoticeVO;

public interface NoticeService {
	//notice
			public List<NoticeVO> getNoticeList(NoticeVO vo); //나의 notice정보 들고오기

}
