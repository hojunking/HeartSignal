package com.hs.meetme.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.sample.domain.SampleVO;

@Mapper
public interface SampleMapper {
	List<SampleVO> getList();
}
