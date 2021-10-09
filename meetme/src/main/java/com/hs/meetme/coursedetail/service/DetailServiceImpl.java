package com.hs.meetme.coursedetail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coursedetail.domain.DetailVO;
import com.hs.meetme.coursedetail.mapper.DetailMapper;

@Service
public class DetailServiceImpl implements DetailService {

	@Autowired DetailMapper detailMapper;
	@Override
	public List<DetailVO> getCourse(int courseId) {
		
		return detailMapper.getCourse(courseId);
	}
//	@Override
//	public DetailVO courseOne(DetailVO vo) {
//		
//		return detailMapper.courseOne(vo);
//	}

}
