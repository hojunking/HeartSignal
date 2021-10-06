package com.hs.meetme.image.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.image.domain.ImageVO;
@Mapper
public interface ImageMapper {
	public int insertImage(ImageVO vo); //사진테이블에 사진등록

}
