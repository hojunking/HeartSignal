package com.hs.meetme.coupleLog.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;

@Mapper
public interface CoupleLogMapper {
	List <CoupleLogVO> getLogList();//로그 전체 불러오기
	List <CoupleLogVO> getLog();//로그 하나 가져오기
	public int logInsert(); //로그 insert
	public int logDelete();
	public int logUpdate();
}
