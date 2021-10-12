package com.hs.meetme.coupleLog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coupleLog.domain.CoupleLogVO;
import com.hs.meetme.coupleLog.mapper.CoupleLogMapper;

@Service
public class CoupleLogServiceImpl implements CoupleLogService {
	
	@Autowired CoupleLogMapper coupleMapper;
	
	@Override
	public List<CoupleLogVO> getLogList(String coupleId) {
		// TODO Auto-generated method stub
		return coupleMapper.getLogList(coupleId);
	}

	@Override
	public CoupleLogVO getLog(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.getLog(vo);
	}
	

	@Override
	public int logInsert(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.logInsert(vo);
	}

	@Override
	public int logInsertImg(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.logInsertImg(vo);
	}
	
	@Override
	public int logInsertLogImg(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.logInsertLogImg(vo);
	}
	@Override
	public int logDelete(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.logDelete(vo);
	}

	@Override
	public int logUpdate(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.logUpdate(vo);
	}

	@Override
	public int imgDelete(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.imgDelete(vo);
	}

	@Override
	public CoupleLogVO getImg(CoupleLogVO vo) {
		// TODO Auto-generated method stub
		return coupleMapper.getImg(vo);
	}





}
