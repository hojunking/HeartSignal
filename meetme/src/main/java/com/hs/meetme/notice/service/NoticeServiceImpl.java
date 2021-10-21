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
	@Override
	public int deleteNotice(NoticeVO noticeVO) {
		return map.deleteNotice(noticeVO);
	}
	@Override
	public int insertNotice(NoticeVO vo) {
		return map.insertNotice(vo);
	}
	@Override
	public List<NoticeVO> coupleRequest(NoticeVO vo) {
		return map.coupleRequest(vo);
	}
	@Override
	public int confirmUpdate(NoticeVO vo) {
		return map.confirmUpdate(vo);
	}
	@Override
	public NoticeVO countNotice(NoticeVO vo) {
		return map.countNotice(vo);
	}

}
