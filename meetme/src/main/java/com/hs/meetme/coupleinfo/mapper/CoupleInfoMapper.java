package com.hs.meetme.coupleinfo.mapper;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;

public interface CoupleInfoMapper {
	public int coupleInfoInsert(CoupleInfoVO vo);  //커플 기본정보 입력
	public CoupleInfoVO coupleInfoSelect(CoupleInfoVO vo); //커플 정보 보여주기
	public int coupleInfoUpdate(CoupleInfoVO vo); //커플 대문이미지, d-day 수정
	public int couplePhotoDefault(CoupleInfoVO vo); //커플 대문사진 초기화
}
