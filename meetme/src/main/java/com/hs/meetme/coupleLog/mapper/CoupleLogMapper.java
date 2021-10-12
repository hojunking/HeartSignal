package com.hs.meetme.coupleLog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;

@Mapper
public interface CoupleLogMapper {
	public List <CoupleLogVO> getLogList(String coupleId);//로그 전체 불러오기
	public CoupleLogVO getLog(CoupleLogVO vo);//로그 하나 가져오기
	public CoupleLogVO getImg(CoupleLogVO vo);
	
	public int logInsert(CoupleLogVO vo); //로그 insert
	public int logInsertImg(CoupleLogVO vo);
	public int logInsertLogImg(CoupleLogVO vo);
	
	public int logDelete(CoupleLogVO vo); // 로그 먼저지우고 이미지를 지우기!
	public int imgDelete(CoupleLogVO vo);
	
	public int logUpdate(CoupleLogVO vo);	// 커플로그 수정하기
}
