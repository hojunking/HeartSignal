package com.hs.meetme.coupleinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;
import com.hs.meetme.coupleinfo.mapper.CoupleInfoMapper;
@Service
public class CoupleInfoServiceImpl implements CoupleInfoService {
	@Autowired CoupleInfoMapper map;
	@Override
	public int coupleInfoInsert(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return map.coupleInfoInsert(vo);
	}

	@Override
	public int coupleInfoUpdate(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return map.coupleInfoUpdate(vo);
	}

	@Override
	public int coupleMatching(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return map.coupleMatching(vo);
	}

	@Override
	public CoupleInfoVO coupleInfoSelect(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return map.coupleInfoSelect(vo);
	}

	@Override
	public int coupleCustomUpdate(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int couplePhotoDefault(CoupleInfoVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CoupleInfoVO read(CoupleInfoVO vo) { //커플 상세정보 보기
		return map.read(vo);
	}

}
