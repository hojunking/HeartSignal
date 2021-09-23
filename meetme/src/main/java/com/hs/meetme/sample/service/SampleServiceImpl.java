package com.hs.meetme.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.sample.domain.SampleVO;
import com.hs.meetme.sample.mapper.SampleMapper;

@Service
public class SampleServiceImpl implements SampleService{

	@Autowired
	SampleMapper sampleDAO;
	
	@Override
	public List<SampleVO> getList() {
		return sampleDAO.getList();
	}

}
