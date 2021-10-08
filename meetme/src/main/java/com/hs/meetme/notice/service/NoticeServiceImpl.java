package com.hs.meetme.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.notice.domain.NoticeVO;
import com.hs.meetme.notice.mapper.NoticeMapper;
@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired NoticeMapper map;
	@Override
	public List<NoticeVO> getNoticeList(NoticeVO vo) {
		return map.getNoticeList(vo);
	}

}
