package com.hs.meetme.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hs.meetme.sample.domain.SampleVO;
import com.hs.meetme.sample.domain.TagVO;

@Mapper
public interface SampleMapper {
	List<SampleVO> getList();
	List<TagVO> getTagList();
}
