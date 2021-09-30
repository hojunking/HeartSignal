package com.hs.meetme.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.main.domain.MainVO;
import com.hs.meetme.main.mapper.MainMapper;

@Service
public class MainServiceImpl implements MainService {
	
	@Autowired MainMapper mainMapper;
	
	@Override
	public List<MainVO> tagList() {
		
		return mainMapper.tagList();
	}

	@Override
	public List<MainVO> placeList() {
		
		return mainMapper.placeList();
	}

}
