package com.hs.meetme.coupleinfo.service;

import com.hs.meetme.coupleinfo.domain.CoupleInfoVO;

public interface CoupleInfoService {
	public int coupleInfoInsert(CoupleInfoVO vo); //첫 커플 기본정보 입력
	public int coupleInfoUpdate(CoupleInfoVO vo); //기존 커플 기본정보 최신화
	public int coupleMatching(CoupleInfoVO vo); //커플 수락시 상태변경
	public CoupleInfoVO coupleInfoSelect(CoupleInfoVO vo); //커플 정보 보여주기
	public int coupleCustomUpdate(CoupleInfoVO vo); //커플 대문이미지, d-day 수정
	public int couplePhotoDefault(int coupleId); //커플 대문사진 초기화
	public CoupleInfoVO read(CoupleInfoVO vo); //커플정보상세보기
	public CoupleInfoVO myLoverInfo(CoupleInfoVO vo); //내여친정보
	public int userCoupleStatusUpdate(CoupleInfoVO vo); //커플상태 업데이트
	public CoupleInfoVO findLove(CoupleInfoVO vo); //내 커플찾기
	
	public CoupleInfoVO userCoupleStatusRead(CoupleInfoVO vo); //유저 커플상태확인
	public int deleteCoupleInfo(CoupleInfoVO vo); //커플정보 삭제
}