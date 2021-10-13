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
	public List<MainVO> placeList(int placeId) {
		
		return mainMapper.placeList(placeId);
	}

	@Override
	public List<MainVO> courseList(String address) {
		
		return mainMapper.courseList(address);
	}

	@Override
	public List<MainVO> addressList() {
		// TODO Auto-generated method stub
		return mainMapper.addressList();
	}

	@Override
	public List<MainVO> addressSec(String address) {
		
		return mainMapper.addressSec(address);
	}

	@Override
	public List<MainVO> regionCourse() {
		// TODO Auto-generated method stub
		return mainMapper.regionCourse();
	}

	@Override
	public List<MainVO> courseListReco() {
		// TODO Auto-generated method stub
		return mainMapper.courseListReco();
	}

}
