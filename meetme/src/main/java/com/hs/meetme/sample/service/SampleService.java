package com.hs.meetme.sample.service;

import java.util.List;

import com.hs.meetme.sample.domain.SampleVO;
import com.hs.meetme.sample.domain.TagVO;


public interface SampleService {
	List<SampleVO> getList();
	List<TagVO> getTagList();
}
