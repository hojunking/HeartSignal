package com.hs.meetme.coupleLog.service;

import java.util.List;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;

public interface CoupleLogService {
	public List <CoupleLogVO> getLogList(String coupleId);//로그 전체 불러오기
	public CoupleLogVO getLog(CoupleLogVO vo);//로그 하나 가져오기
	
	public int logInsert(CoupleLogVO vo); //로그 insert
	public int logInsertImg(CoupleLogVO vo);
	public int logInsertLogImg(CoupleLogVO vo);
	
	public int logDelete(CoupleLogVO vo);
	public int logUpdate(CoupleLogVO vo); // 커플로그 수정하기
}
