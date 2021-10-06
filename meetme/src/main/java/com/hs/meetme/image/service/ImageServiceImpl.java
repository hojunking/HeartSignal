package com.hs.meetme.image.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.meetme.image.domain.ImageVO;
import com.hs.meetme.image.mapper.ImageMapper;
@Service
public class ImageServiceImpl implements ImageService{
	@Autowired ImageMapper map;
	
	
	@Override
	public int insertImage(ImageVO vo) { //이미지테이블에 이미지 등록
		return map.insertImage(vo); 		//imgUrl만 필요합니다.
	}
}
