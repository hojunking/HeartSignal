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
		return map.coupleInfoInsert(vo);
	}

	@Override
	public int coupleInfoUpdate(CoupleInfoVO vo) {
		return map.coupleInfoUpdate(vo);
	}

	@Override
	public int coupleMatching(CoupleInfoVO vo) {
		return map.coupleMatching(vo);
	}

	@Override
	public CoupleInfoVO coupleInfoSelect(CoupleInfoVO vo) {
		return map.coupleInfoSelect(vo);
	}

	@Override
	public int coupleCustomUpdate(CoupleInfoVO vo) {
		return map.coupleCustomUpdate(vo);
	}

	@Override
	public int couplePhotoDefault(int coupleId) {
		return map.couplePhotoDefault(coupleId);
	}

	@Override
	public CoupleInfoVO read(CoupleInfoVO vo) { //커플 상세정보 보기
		return map.read(vo);
	}

	@Override
	public CoupleInfoVO myLoverInfo(CoupleInfoVO vo) {
		return map.myLoverInfo(vo);
	}

	@Override
	public int userCoupleStatusUpdate(CoupleInfoVO vo) {
		return map.userCoupleStatusUpdate(vo);
	}

	@Override
	public CoupleInfoVO userCoupleStatusRead(CoupleInfoVO vo) {
		return map.userCoupleStatusRead(vo);
	}

	@Override
	public int deleteCoupleInfo(CoupleInfoVO vo) {
		return map.deleteCoupleInfo(vo);
	}

	@Override
	public CoupleInfoVO findLove(CoupleInfoVO vo) {
		return map.findLove(vo);
	}

}
