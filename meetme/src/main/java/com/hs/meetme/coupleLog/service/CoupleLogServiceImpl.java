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
	public List<CoupleLogVO> getLogList() {
		// TODO Auto-generated method stub
		return coupleMapper.getLogList();
	}

	@Override
	public List<CoupleLogVO> getLog() {
		// TODO Auto-generated method stub
		return coupleMapper.getLog();
	}

	@Override
	public int logInsert() {
		// TODO Auto-generated method stub
		return coupleMapper.logInsert();
	}

	@Override
	public int logDelete() {
		// TODO Auto-generated method stub
		return coupleMapper.logDelete();
	}

	@Override
	public int logUpdate() {
		// TODO Auto-generated method stub
		return coupleMapper.logUpdate();
	}

}
